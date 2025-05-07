public class Vertex {
    private final double xCoord;
    private final double yCoord;
    private final String vertex;

    public Vertex(double xCoord, double yCoord, String vertex) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.vertex = vertex;
    }

    public double getXCoord() {
        return xCoord;
    }

    public double getYCoord() {
        return yCoord;
    }

    public String getVertex() {
        return vertex;
    }
}
