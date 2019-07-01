package parser;

import java.math.BigDecimal;

class Venda implements Comparable<Venda> {
    private static final String OPEN = "[";
    private static final String CLOSE = "]";
    private static final String EMPTY = "";
    private static final String COMMA = ",";
    private static final String DASH = "-";

    private Long id;
    private BigDecimal cost;
    private Vendedor vendedor;

    static Venda createFrom(String[] parsedEntityData, Vendedor vendedor) {
        if (null != parsedEntityData) {
            Venda venda = VendaBuilder.aVenda()
                    .withId(Long.parseLong(parsedEntityData[1]))
                    .withCost(getCost(parsedEntityData[2]))
                    .withSalesman(vendedor)
                    .build();

            vendedor.setVenda(venda);

            return venda;
        }
        return null;
    }

    @Override
    public int compareTo(Venda venda) {
        return venda.getCost().compareTo(this.getCost());
    }

    private static BigDecimal getCost(String entityItens) {
        BigDecimal value = BigDecimal.ZERO;
        String[] parsedEntityItens =
                entityItens.replace(OPEN, EMPTY).replace(CLOSE, EMPTY).split(COMMA);

        if (parsedEntityItens.length > 0) {
            for (String parsedEntityItem : parsedEntityItens) {
                String[] parts = parsedEntityItem.split(DASH);
                if (parts.length == 3) {
                    value = value.add(new BigDecimal(parts[1])
                            .multiply(new BigDecimal(parts[2])));
                }
            }
        }
        return value;
    }

    private Venda(Long id, BigDecimal cost, Vendedor vendedor) {
        this.id = id;
        this.cost = cost;
        this.vendedor = vendedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }

    public static final class VendaBuilder {
        private Long id;
        private BigDecimal cost;
        private Vendedor vendedor;

        private VendaBuilder() {
        }

        public static VendaBuilder aVenda() {
            return new VendaBuilder();
        }

        public VendaBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public VendaBuilder withCost(BigDecimal cost) {
            this.cost = cost;
            return this;
        }

        public VendaBuilder withSalesman(Vendedor vendedor) {
            this.vendedor = vendedor;
            return this;
        }

        public Venda build() {
            return new Venda(id, cost, vendedor);
        }
    }
}
