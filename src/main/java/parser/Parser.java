package parser;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Parser {
   
   private static final String SEPARADOR = "\\u00E7";
   private static final String VENDEDOR_ID = "001";
   private static final String CLIENTE_ID = "002";
   private static final String VENDA_ID = "003";
   
   private Set<Cliente> clientes;
   private Set<Vendedor> vendedores;
   private Set<Venda> vendas;

   public Parser(){
      this.clientes = new HashSet<>();
      this.vendedores = new HashSet<>();
      this.vendas = new TreeSet<>();
   }

   public void parse(String entityData){
      String[] parsedEntityData = entityData.split(SEPARADOR);
      if (parsedEntityData.length == 4) {
         parseEntityBasedOnItsType(parsedEntityData);
         System.out.println("Line parsed: " + entityData);
      } else {
         System.err.println("Invalid parser data: " + entityData);
      }
   }

   public Relatorio buildRelatorio(){
      Relatorio relatorio = null;
      if(!this.clientes.isEmpty()) {
         relatorio = Relatorio.RelatorioBuilder.anOutput()
             .withTotalClientes(this.clientes.size())
             .withTotalVendedores(this.vendedores.size())
             .withIdVendaMaisCara(getMaiorVenda())
             .withNomePiorVendedor(getNomePiorVendedor())
             .build();
      }
      this.clearData();
      return relatorio;
   }

   private void parseEntityBasedOnItsType(String[] parsedEntityData) {
      if (VENDEDOR_ID.equals(parsedEntityData[0])) {
         Vendedor vendedor = Vendedor.createFrom(parsedEntityData);
         this.vendedores.add(vendedor);
      } else if (CLIENTE_ID.equals(parsedEntityData[0])) {
         Cliente cliente = Cliente.createFrom(parsedEntityData);
         this.clientes.add(cliente);
      } else if (VENDA_ID.equals(parsedEntityData[0])) {
         Venda venda = Venda.createFrom(parsedEntityData,
             getVendedorPorNome(parsedEntityData[3]));
         this.vendas.add(venda);
      }
   }

   private Vendedor getVendedorPorNome(String name){
      for (Vendedor vendedor : this.vendedores){
         if (vendedor.getName().equals(name)){
            return vendedor;
         }
      }
      return null;
   }

   private Long getMaiorVenda(){
      Long vendaMaisCaraID = null;
      if (this.vendas.size() > 0){
         Venda vendaMaisCara = ((TreeSet<Venda>)this.vendas).first();
         vendaMaisCaraID = vendaMaisCara.getId();
      }
      return vendaMaisCaraID;
   }

   private String getNomePiorVendedor(){
      String nomePiorVendedor = null;
      if (this.vendedores.size() > 0){
         TreeSet<Vendedor> sortedVendedores = new TreeSet<>(this.vendedores);
         Vendedor piorVendedor = sortedVendedores.first();
         nomePiorVendedor = piorVendedor.getName();
         sortedVendedores.clear();
      }
      return nomePiorVendedor;
   }

   private void clearData(){
      this.clientes.clear();
      this.vendedores.clear();
      this.vendas.clear();
   }
}
