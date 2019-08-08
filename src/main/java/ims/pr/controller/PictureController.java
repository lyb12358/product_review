package ims.pr.controller;

import ims.pr.pojo.ReviewProduct;
import ims.pr.service.ProductService;
import ims.pr.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
@RestController
public class PictureController {


    @Autowired
    public ProductService prodService;

    public static String getAddress() {
        if (System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0) {
            return "/home/server001/imsfile";
        } else if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
            return "G:/image";
        } else {
            return "/Users/lyb/Desktop/image";
        }
    }

    // 款式图片
    @RequestMapping("/imageUpload/product")
    public ResponseBean uploadProdPic(HttpServletRequest request, HttpServletResponse response) {
        try {
            MultipartHttpServletRequest imageRequest = (MultipartHttpServletRequest) request;
            MultipartFile uploadFile = imageRequest.getFile("file");
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());

            // 原始图像的宽度和高度
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            Integer id = Integer.valueOf(request.getHeader("id"));
            // String styleName = request.getParameter("styleName");
            // System.out.println("宽：" + width + "，高：" + height + "，id：" + id +
            // "，款号：" + prodStyle + "，款名：" + styleName);

            if ((int) uploadFile.getSize() > 10 * 1024 * 1024) {
                return new ResponseBean(60000, "图片不能大于5MB", null);
            }
//            if (height < 1000 | width < 1000) {
//                return new ResponseBean(60001, "图片尺寸不合格，长或宽至少需要1000像素", null);
//            }
            if (height != width) {
                return new ResponseBean(60002, "图片必须为正方形", null);
            }
            // 上传主图
            String originalFilename = uploadFile.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            log.info(ext);
            // String filePath =
            // request.getSession().getServletContext().getRealPath("/") +
            // "WEB-INF/image/style/" + id
            // + "/";
            String filePath = getAddress() + "/" + id + "/";
            delAllFile(filePath);
            String dateId = String.valueOf(System.currentTimeMillis());
            String imageName = id + dateId + ext;
            log.info(filePath + imageName);
            File file = new File(filePath + imageName);
            //fuck off!!!
            File file2 = new File(filePath);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            uploadFile.transferTo(file);

            // 压缩系数
            float resizeTimes = 1f;
            // 调整后的图片的宽度和高度
            int toWidth = (int) (768 * resizeTimes);
            int toHeight = (int) (768 * resizeTimes);
            // 生成缩略图
            BufferedImage smallImage = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
            smallImage.getGraphics().drawImage(
                    bufferedImage.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            String formatName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String thumbnailName = id + dateId + "thumbnail" + ext;
            ImageIO.write(smallImage, formatName /* format desired */,
                    new File(filePath + thumbnailName) /* target */);
            // 数据库记录主图和缩略图的名字
            ReviewProduct prod = prodService.getProdById(id);
            prod.setImage(imageName);
            prod.setThumbnail(thumbnailName);
            prodService.addProduct(prod);
            return new ResponseBean(20000, "图片上传成功", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean(60002, "图片上传失败", null);
        }

    }

    // 删除文件夹下所有文件
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            temp = new File(path + tempList[i]);
            if (temp.isFile()) {
                temp.delete();
            }
        }
        return flag;
    }
}
