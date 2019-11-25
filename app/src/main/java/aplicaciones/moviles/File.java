package aplicaciones.moviles;

import android.graphics.Bitmap;

public class File {

    private static File file = null;
    private static Bitmap image = null;

    protected File() {
    }

    public static File getInstance() {
        if (file == null) {
            file = new File();
        }
        return file;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
