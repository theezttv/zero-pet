import zero.Color;
import zero.GameApp;
import zero.ImageRegion;
import zero.Key;

public class Main extends GameApp {
    private ImageRegion petImage;
    private double x = 280;

    @Override public void settings() { size(640, 400); }
    @Override public void setup() {
        var sheet = loadImage("/pet/bird-flap-sheet.png");
        petImage = imageRegion(sheet, 0, 0, 701, 561);
    }

    @Override public void update(double seconds) {
        if (keyDown(Key.LEFT)) x -= 160 * seconds;
        if (keyDown(Key.RIGHT)) x += 160 * seconds;
        x = Math.max(0, Math.min(x, width() - 80));
    }


    @Override public void draw() {
        background(Color.rgb(240, 248, 255));
        image(petImage, x, 150, 80, 64);
    }

    public static void main(String[] args) { launch(args); }
}
