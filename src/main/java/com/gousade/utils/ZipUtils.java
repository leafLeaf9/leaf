package com.gousade.utils;

import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
	private ZipUtils() {
	}

	/**
	 * 压缩文件
	 *
	 * @param filePath 待压缩的文件路径
	 * @return 压缩后的文件
	 */
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static File zip(String filePath, String zipName) {

		File target;
		target = new File(zipName);
		if (target.exists()) {
			target.delete(); // 删除旧的文件
		}
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(target);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		zip(filePath, outStream);
		closeQuietly(outStream);
		return target;
	}

	public static void zip(String filePath, OutputStream outStream) {
		File source = new File(filePath);
		if (source.exists()) {
			// 压缩文件名=源文件名.zip
			ZipOutputStream zos = null;
			try {
				zos = new ZipOutputStream(new BufferedOutputStream(outStream));
				// 添加对应的文件Entry
				addEntry("", source, zos);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				closeQuietly(zos);
			}
		}
	}


	/**
	 * 扫描添加文件Entry
	 *
	 * @param base   基路径
	 * @param source 源文件
	 * @param zos    Zip文件输出流
	 */
	private static void addEntry(String base, File source, ZipOutputStream zos)
			throws IOException {
		// 按目录分级，形如：/aaa/bbb.txt
		if (source.isDirectory()) {
			if (base != null && base.length() > 0) {
				base = base + "/";
			}
			for (File file : Objects.requireNonNull(source.listFiles())) {
				if (file.getName().equalsIgnoreCase("cvs") || file.getName().endsWith(".crc")) {
					continue;
				}
				// 递归列出目录下的所有文件，添加文件Entry
				addEntry(base + file.getName(), file, zos);
			}
		} else {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				byte[] buffer = new byte[1024 * 10];
				fis = new FileInputStream(source);
				bis = new BufferedInputStream(fis, buffer.length);
				int read;
				zos.putNextEntry(new ZipEntry(base));
				while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
					zos.write(buffer, 0, read);
				}
				zos.closeEntry();
			} finally {
				closeQuietly(bis, fis);
			}
		}
	}

	/**
	 * 解压文件
	 *
	 * @param zipFilePath 压缩文件路径
	 */
	public static void unzip(String zipFilePath, String upZipPath) {
		File source = new File(zipFilePath);
		if (source.exists()) {
			try {
				InputStream inStream = new FileInputStream(source);
				unzip(inStream, upZipPath);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} else {
			throw new RuntimeException("待解压文件不存在：" + zipFilePath);
		}
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void unzip(InputStream inStream, String zipPath) {
		ZipInputStream zis = null;
		BufferedOutputStream bos = null;
		try {
			zis = new ZipInputStream(inStream);
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				File target = new File(zipPath, entry.getName());
				if (!target.getParentFile().exists()) {
					// 创建文件父目录
					target.getParentFile().mkdirs();
				}
				// 写入文件
				bos = new BufferedOutputStream(new FileOutputStream(target));
				int read;
				byte[] buffer = new byte[1024 * 10];
				while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
					bos.write(buffer, 0, read);
				}
				bos.flush();
			}
			zis.closeEntry();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			closeQuietly(zis, bos);
		}
	}

	/**
	 * 关闭一个或多个流对象
	 *
	 * @param closeables 可关闭的流对象列表
	 */
	public static void close(Closeable... closeables) throws IOException {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					closeable.close();
				}
			}
		}
	}

	/**
	 * 关闭一个或多个流对象
	 *
	 * @param closeables 可关闭的流对象列表
	 */
	public static void closeQuietly(Closeable... closeables) {
		try {
			close(closeables);
		} catch (IOException e) {
			// do nothing
		}
	}
}
