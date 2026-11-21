import zero.Color;
import zero.GameApp;
import zero.ImageRegion;
import zero.Key;
import zero.SpriteAnimation;


public class Main extends GameApp {
    private SpriteAnimation petAnimation;
    private double x = 280;

    @Override public void settings() { size(640, 400); }
    @Override public void setup() {
        var sheet = loadImage("/pet/bird-flap-sheet.png");
        ImageRegion[] frames = {
            imageRegion(sheet, 0, 0, 701, 561),
            imageRegion(sheet, 701, 0, 701, 561),
            imageRegion(sheet, 0, 531, 701, 561),
            imageRegion(sheet, 701, 531, 701, 561)
        };
        petAnimation = new SpriteAnimation(8, frames);
    }

    @Override public void update(double seconds) {
        if (keyDown(Key.LEFT)) x -= 160 * seconds;
        if (keyDown(Key.RIGHT)) x += 160 * seconds;
        x = Math.max(0, Math.min(x, width() - 80));
        petAnimation.update(seconds);
    }


    @Override public void draw() {
        background(Color.rgb(240, 248, 255));
        image(petAnimation, x, 150, 80, 64);
    }

    public static void main(String[] args) { launch(args); }
}
