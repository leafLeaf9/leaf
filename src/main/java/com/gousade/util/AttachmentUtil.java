package com.gousade.util;

import com.gousade.pojo.util.AttachmentGeneral;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author woxi-Gisard
 * @date 2020年8月11日 下午9:38:44
 */
public class AttachmentUtil {

    public static AttachmentGeneral AttachmentUpload(MultipartFile file) {
        AttachmentGeneral attachmentGeneral = new AttachmentGeneral();
        if (!file.isEmpty()) {
            String base = "D:" + File.separator + "gousadeFiles" + File.separator;
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            base += "generalfile" + File.separator;
            String dateDtr = df.format(new Date());
            base += dateDtr;
            File savedir = new File(base);
            if (!savedir.exists())
                savedir.mkdirs();
            String oriName = file.getOriginalFilename();
            String ext = oriName.substring(oriName.lastIndexOf('.') + 1).toLowerCase();

            String filename = UUID.randomUUID().toString().replace("-", "");
            filename += "." + ext;
            String absolutePath = base + File.separator + filename;
            File absoluteFile = new File(absolutePath);
            try {
                file.transferTo(absoluteFile);// 此方法代替下面的文件工具类将文件写入磁盘
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
//			FileUtils.SaveFileFromInputStream(file.getInputStream(), base, filename);
            attachmentGeneral.setAttachName(oriName.substring(0, oriName.lastIndexOf('.')));
            attachmentGeneral.setAttachType(ext);
            long size = 0l;
            String sSize = "";
            if (file.getSize() >= 1024 * 1024) {
                size = file.getSize() / (1024 * 1024);
                sSize = size + "MB";
            } else if (file.getSize() >= 1024) {
                size = file.getSize() / (1024);
                sSize = size + "KB";
            } else {
                sSize = size + "B";
            }
            attachmentGeneral.setId(SaltUtil.generateUUId());
            attachmentGeneral.setAttachSize(sSize);
            attachmentGeneral.setAttachPath(absolutePath);
        }
        return attachmentGeneral;
    }

}
