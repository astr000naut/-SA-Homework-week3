
import java.util.HashMap;

public class PlayList {
 
    HashMap<String, Play> plays;

    PlayList() {
        plays = new HashMap<String, Play>();
    }

    public void addPlay(String id, String name, String type) {
        Play play = new Play(name, type);
        plays.put(id, play);
    }

    public Play getPlay(String id) {
        return plays.get(id);
    }
}
