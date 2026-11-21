import zero.Color;
import zero.GameApp;
import zero.ImageRegion;
import zero.Key;
import zero.Sound;
import zero.SpriteAnimation;
import zero.ui.Button;


public class Main extends GameApp {
    private Pet pet;
    private Sound chime;
    private boolean playing = false;
    private Button navigation;

    @Override public void settings() { size(640, 400); }
    @Override public void setup() {
        var sheet = loadImage("/pet/bird-flap-sheet.png");
        ImageRegion[] frames = {
            imageRegion(sheet, 0, 0, 701, 561),
            imageRegion(sheet, 701, 0, 701, 561),
            imageRegion(sheet, 0, 531, 701, 561),
            imageRegion(sheet, 701, 531, 701, 561)
        };
        pet = new Pet(new SpriteAnimation(8, frames));
        chime = loadSound("/pet/chime.wav");
        chime.setVolume(0.25);
        navigation = new Button("Start");
        navigation.onClick(this::switchScreen);
        controls(navigation);
    }

    private void switchScreen() {
        playing = !playing;
        navigation.setText(playing ? "Back" : "Start");
    }

    @Override public void update(double seconds) {
        if (!playing) return;
        int direction = 0;
        if (keyDown(Key.LEFT)) direction--;
        if (keyDown(Key.RIGHT)) direction++;
        pet.update(direction, seconds, width());
        if (keyPressed(Key.SPACE)) {
            chime.stop();
            chime.play();
        }
    }

    @Override public void draw() {
        if (!playing) {
            background(Color.rgb(19, 24, 55));
            fill(Color.rgb(255, 224, 145));
            circle(520, 70, 70);
            fill(Color.WHITE);
            textSize(38);
            text("MY PET GAME", 55, 205);
            textSize(18);
            text("Press Start to meet your pet", 57, 242);
            return;
        }
        background(Color.rgb(240, 248, 255));
        fill(Color.rgb(47, 79, 79));
        textSize(20);
        text("Click the canvas; arrows move, Space chimes.", 20, 30);
        pet.draw(this);
    }

    public static void main(String[] args) { launch(args); }
}
