package utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

public class Ximage {

    public static Image getAppIcon() {
        URL url = Ximage.class.getResource("/images/fpt.png");
        return new ImageIcon(url).getImage();
    }
    
    // Lưu file ảnh đã chọn vào folder images
    public static void save(File src) {
        File dst = new File("images", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

//    public static ImageIcon read(String fileName) {
//        File path = new File("logos", fileName);
//        return new ImageIcon(path.getAbsolutePath());
//    }
    
    // Đọc file ảnh
    public static ImageIcon read(String fileName, int labelWidth, int labelHeight) {
        // Đọc file ảnh
        File path = new File("images", fileName);
        ImageIcon imageIcon = new ImageIcon(path.getAbsolutePath());

        // Lấy Image từ ImageIcon
        Image image = imageIcon.getImage();

        // Tính toán tỷ lệ để giữ nguyên tỷ lệ của ảnh
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double widthRatio = (double) labelWidth / imageWidth;
        double heightRatio = (double) labelHeight / imageHeight;
        double ratio = Math.min(widthRatio, heightRatio);

        // Tính toán kích thước mới của ảnh
        int newWidth = (int) (imageWidth * ratio);
        int newHeight = (int) (imageHeight * ratio);

        // Tạo một Image mới với kích thước đã tính toán
        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Tạo lại ImageIcon từ Image đã thay đổi kích thước
        return new ImageIcon(scaledImage);
    }

}
