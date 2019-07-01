package parser;

public class Relatorio {
   private static final String TC_MESSAGE = "Quantidade de clientes no arquivo de entrada: ";
   private static final String TS_MESSAGE = "Quantidade de vendedores no arquivo de entrada: ";
   private static final String MESI_MESSAGE = "ID da venda mais cara: ";
   private static final String WSN_MESSAGE = "O pior vendedor: ";

   private int totalClientes;
   private int totalVendedores;
   private long idVendaMaisCara;
   private String nomePiorVendedor;

   public Relatorio(int totalClientes, int totalVendedores, long idVendaMaisCara, String nomePiorVendedor) {
      this.totalClientes = totalClientes;
      this.totalVendedores = totalVendedores;
      this.idVendaMaisCara = idVendaMaisCara;
      this.nomePiorVendedor = nomePiorVendedor;
   }

   public int getTotalClientes() {
      return totalClientes;
   }

   public void setTotalClientes(int totalClientes) {
      this.totalClientes = totalClientes;
   }

   public int getTotalVendedores() {
      return totalVendedores;
   }

   public void setTotalVendedores(int totalVendedores) {
      this.totalVendedores = totalVendedores;
   }

   public long getIdVendaMaisCara() {
      return idVendaMaisCara;
   }

   public void setIdVendaMaisCara(long idVendaMaisCara) {
      this.idVendaMaisCara = idVendaMaisCara;
   }

   public String getNomePiorVendedor() {
      return nomePiorVendedor;
   }

   public void setNomePiorVendedor(String nomePiorVendedor) {
      this.nomePiorVendedor = nomePiorVendedor;
   }

   @Override
   public String toString() {
      return "Relatorio{" +
              "totalClientes=" + totalClientes +
              ", totalVendedores=" + totalVendedores +
              ", idVendaMaisCara=" + idVendaMaisCara +
              ", nomePiorVendedor='" + nomePiorVendedor + '\'' +
              '}';
   }

   public String getMensagemTotalClientes() {
      return TC_MESSAGE + this.totalClientes;
   }
   public String getMensagemTotalVendedores() {
      return TS_MESSAGE + this.totalVendedores;
   }
   public String getMensagemIdVendaMaisCara() {
      return MESI_MESSAGE + this.idVendaMaisCara;
   }
   public String getMensagemNomePiorVendedor() {
      return WSN_MESSAGE + this.nomePiorVendedor;
   }


   public static final class RelatorioBuilder {
      private int totalClientes;
      private int totalVendedores;
      private long idVendaMaisCara;
      private String nomePiorVendedor;

      private RelatorioBuilder() {
      }

      public static RelatorioBuilder anOutput() {
         return new RelatorioBuilder();
      }

      public RelatorioBuilder withTotalClientes(int totalClientes) {
         this.totalClientes = totalClientes;
         return this;
      }

      public RelatorioBuilder withTotalVendedores(int totalVendedores) {
         this.totalVendedores = totalVendedores;
         return this;
      }

      public RelatorioBuilder withIdVendaMaisCara(long idVendaMaisCara) {
         this.idVendaMaisCara = idVendaMaisCara;
         return this;
      }

      public RelatorioBuilder withNomePiorVendedor(String nomePiorVendedor) {
         this.nomePiorVendedor = nomePiorVendedor;
         return this;
      }

      public Relatorio build() {
         return new Relatorio(totalClientes, totalVendedores, idVendaMaisCara, nomePiorVendedor);
      }
   }
}
