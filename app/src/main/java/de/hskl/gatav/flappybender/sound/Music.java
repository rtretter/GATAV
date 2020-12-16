package de.hskl.gatav.flappybender.sound;

public class Music {

    private boolean loop;
    private int id;

    public Music(int id, boolean loop) {
        this.id = id;
        this.loop = loop;
    }

    public int getId() {
        return id;
    }

    public boolean loop() {
        return loop;
    }

}
