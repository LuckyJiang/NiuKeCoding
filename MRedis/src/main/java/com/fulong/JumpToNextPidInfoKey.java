package fulong;

public class JumpToNextPidInfoKey extends BasePrefix {

    public JumpToNextPidInfoKey(String prefix) {
        super(prefix);
    }

    public JumpToNextPidInfoKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static JumpToNextPidInfoKey getByHandle = new JumpToNextPidInfoKey("handle");

}
