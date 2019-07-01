package parser;

class Cliente {
   private long cnpj;
   private String name;
   private String businessArea;

   public long getCnpj() {
      return cnpj;
   }

   public void setCnpj(long cnpj) {
      this.cnpj = cnpj;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getBusinessArea() {
      return businessArea;
   }

   public void setBusinessArea(String businessArea) {
      this.businessArea = businessArea;
   }

   static Cliente createFrom(String[] parsedEntityData){
      if(null != parsedEntityData) {

         return ClienteBuilder.aCliente()
                 .withCnpj(Long.parseLong(parsedEntityData[1]))
                 .withName(parsedEntityData[2])
                 .withBusinessArea(parsedEntityData[3])
                 .build();
      }

      return null;
   }

   @Override
   public String toString() {
      return "Cliente{" +
              "cnpj=" + cnpj +
              ", name='" + name + '\'' +
              ", businessArea='" + businessArea + '\'' +
              '}';
   }


   public static final class ClienteBuilder {
      private long cnpj;
      private String name;
      private String businessArea;

      private ClienteBuilder() {
      }

      public static ClienteBuilder aCliente() {
         return new ClienteBuilder();
      }

      public ClienteBuilder withCnpj(long cnpj) {
         this.cnpj = cnpj;
         return this;
      }

      public ClienteBuilder withName(String name) {
         this.name = name;
         return this;
      }

      public ClienteBuilder withBusinessArea(String businessArea) {
         this.businessArea = businessArea;
         return this;
      }

      public Cliente build() {
         Cliente cliente = new Cliente();
         cliente.setCnpj(cnpj);
         cliente.setName(name);
         cliente.setBusinessArea(businessArea);
         return cliente;
      }
   }
}
