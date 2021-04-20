package barracksWars.core.commands;

import barracksWars.interfaces.Executable;

public abstract class Command implements Executable {
    private String[] data;

    protected Command(String[] data){
        this.data = data;

    }

    public String[] getData() {
        return this.data;
    }
}
