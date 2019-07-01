package parser;

import java.math.BigDecimal;

class Vendedor implements Comparable<Vendedor> {
    private long cpf;
    private String name;
    private BigDecimal salary;
    private Venda venda;

    private Vendedor(long cpf, String name, BigDecimal salary, Venda venda) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
        this.venda = venda;
    }

    static Vendedor createFrom(String[] parsedEntityData) {
        if (null != parsedEntityData) {
            return VendedorBuilder.aVendedor()
                    .withCpf(Long.parseLong(parsedEntityData[1]))
                    .withName(parsedEntityData[2])
                    .withSalary(new BigDecimal(parsedEntityData[3]))
                    .build();
        }

        return null;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "cpf=" + cpf +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", venda=" + venda +
                '}';
    }

    @Override
    public int compareTo(Vendedor vendedor) {
        return this.getVenda().getCost().compareTo(
                vendedor.getVenda().getCost());
    }

    public static final class VendedorBuilder {
        private long cpf;
        private String name;
        private BigDecimal salary;
        private Venda venda;

        private VendedorBuilder() {
        }

        public static VendedorBuilder aVendedor() {
            return new VendedorBuilder();
        }

        public VendedorBuilder withCpf(long cpf) {
            this.cpf = cpf;
            return this;
        }

        public VendedorBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public VendedorBuilder withSalary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public VendedorBuilder withSale(Venda venda) {
            this.venda = venda;
            return this;
        }

        public Vendedor build() {
            return new Vendedor(cpf, name, salary, venda);
        }
    }
}


