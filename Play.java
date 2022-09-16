public class Play {

    private String name;
    private String type ;

    Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    String getName() {
        return this.name;
    }
    
    String getType() {
        return this.type;
    }
}