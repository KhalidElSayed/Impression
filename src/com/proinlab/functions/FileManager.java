package com.proinlab.functions;

import java.io.File;
import java.util.Arrays;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * SDCARD/.Impression/ Category Name / File Type / FileDir / 1~n.png
 * 
 * @author PROIN LAB
 */

public class FileManager {

	private static String SD_CARD_DIRECTORY = B.SDCARD_DIRECTORY;

	/**
	 * SDī�� , �Է��� ��η� ����
	 * 
	 * @param Directory
	 * @return
	 */

	public static boolean CREATE_FOLDER(String Directory) {
		File FILE = new File(Directory);
		if (!FILE.exists())
			while (FILE.mkdirs())
				;
		return true;
	}

	/**
	 * ���� ��ΰ� �ֳ� Ȯ��
	 * 
	 * @param Category
	 *            : ������ ī�װ�
	 * @param Type
	 *            : ������ Ÿ��, int Basics.EDIT_TYPE_----
	 * @param FileDir
	 *            : ���� ���
	 * @return
	 */

	public boolean IS_EXIST_FILE_FODLER(String FileDir) {
		File FILE = new File(SD_CARD_DIRECTORY + "FILE/" + FileDir);
		if (FILE.exists())
			return true;
		return false;
	}

	/**
	 * ���� ��ΰ� �ֳ� Ȯ��
	 * 
	 * @param FileDir
	 *            : ���� ���
	 * @return
	 */

	public boolean IS_EXIST_FILE(String FileDir) {
		File FILE = new File(FileDir);
		if (FILE.exists())
			return true;
		return false;
	}

	/**
	 * ���Ͽ� ���� ���� ����
	 * 
	 * @param Category
	 *            : ������ ī�װ�
	 * @param Type
	 *            : ������ Ÿ��, int Basics.EDIT_TYPE_xxxx
	 * @param FileDir
	 *            : ���� ���
	 * @return �����ϸ� true
	 */

	public boolean CREATE_FILE_FODLER(String FileDir) {

		File FILE = new File(SD_CARD_DIRECTORY + "FILE/" + FileDir);
		if (!FILE.exists())
			while (FILE.mkdirs())
				;
		return true;
	}

	/**
	 * ��ο� �ִ� ���� ����
	 * 
	 * @param Directory
	 * @return
	 */

	public boolean DELETE_FOLDER(String Directory) {
		String mPath = SD_CARD_DIRECTORY + Directory;
		DeleteDir(mPath);
		return true;
	}

	/**
	 * ������ ���� ����
	 * 
	 * @param FileDir
	 *            : ���� ���
	 * @return
	 */

	public boolean DELETE_FILE_FODLER(String FileDir) {

		String mPath = SD_CARD_DIRECTORY + "FILE/" + FileDir;
		DeleteDir(mPath);

		return true;
	}

	public static void DeleteDir(String path) {
		File file = new File(path);
		if (!file.exists())
			return;

		File[] childFileList = file.listFiles();
		for (File childFile : childFileList) {
			if (childFile.isDirectory()) {
				DeleteDir(childFile.getAbsolutePath());
			} else {
				childFile.delete();
			}
		}
		file.delete();
	}

	/**
	 * TYPE : CANVAS �ϰ�� �����Ѵ�
	 * 
	 * @param foreground
	 * @param background
	 * @param saveDir
	 * @return
	 */
	public boolean WORKSPACE_SAVE_CANVAS(Bitmap foreground, Bitmap background,
			String saveDir) {
		File file = new File(saveDir);
		if (!file.exists())
			while (file.mkdir())
				;
		String savePath = saveDir + B.SAVE_FILE_NAME_CANVAS_BG;
		BitmapProcess bp = new BitmapProcess();
		if (background != null)
			bp.saveBitmapPNG(savePath, background);
		savePath = saveDir + B.SAVE_FILE_NAME_CANVAS_FG;
		if (foreground != null)
			bp.saveBitmapPNG(savePath, foreground);
		return true;
	}

	/**
	 * TYPE : NOTE �ϰ�� �����Ѵ�
	 * 
	 * @param foreground
	 * @param background
	 *            : ��� �ٲ����ø� ����
	 * @param saveDir
	 *            : ���ϸ���� ���� ���
	 * @return
	 */
	public boolean WORKSPACE_SAVE_NOTE(Bitmap foreground, Bitmap background,
			String saveDir) {
		File file = new File(saveDir);
		if (!file.exists())
			while (file.mkdir())
				;
		String savePath = saveDir;
		BitmapProcess bp = new BitmapProcess();
		if (background != null)
			bp.saveBitmapPNG(savePath, background);
		savePath = saveDir;
		if (foreground != null)
			bp.saveBitmapPNG(savePath, foreground);
		return true;
	}

	/**
	 * ��η� ���� �̹����� ������ ���ϴ� ��η� ����� �����Ѵ� �ش� ��η� ����ȴ�
	 * 
	 * @param imgDir
	 *            �̹����� ���
	 * @param saveDir
	 *            ������ ���, ���ϸ�, Ȯ���ڱ���
	 * @param width
	 *            ���ϴ� ���α���
	 * @param height
	 *            ���ϴ� ���α���
	 * @return ���п���
	 */
	public boolean PNG_SAVE_BY_DIR(String imgDir, String saveDir, int width,
			int height) {
		File file = new File(saveDir);
		if (!file.exists())
			while (file.mkdir())
				;
		BitmapProcess bp = new BitmapProcess();
		Bitmap bitmap = bp.loadBackgroud(imgDir, width, height);
		bitmap = bp.ResizeBitmapMatchCanvas(bitmap, width, height);

		bp.saveBitmapPNG(saveDir, bitmap);

		return true;
	}

	/**
	 * ��ο� �ִ� �̹����� ȸ�����Ѽ� �����Ѵ�
	 * 
	 * @param imgDir
	 *            �̹����� ���
	 * @return ���п���
	 */
	public boolean PNG_ROTATE_BY_DIR(String imgDir) {
		BitmapProcess bp = new BitmapProcess();
		Bitmap bitmap = bp.loadImg(imgDir, 1);
		if (bitmap == null)
			return false;
		bitmap = bp.imgRotate180(bitmap);
		bp.saveBitmapPNG(imgDir, bitmap);
		return true;
	}

	/**
	 * ��ο� �ִ� �̹����� ���İ��� ������� _alpha.png �� �����Ѵ�
	 * 
	 * @param imgDir
	 *            �̹����� ��ü ���
	 * @param parentPath
	 *            �̹����� �θ� ���
	 * @param alpha
	 * @return
	 */
	public boolean PNG_ALPHACHANGE_BY_DIR(String imgDir, String parentPath,
			int alpha) {
		BitmapProcess bp = new BitmapProcess();
		Bitmap bitmap = bp.loadImg(imgDir, 1);
		if (bitmap == null)
			return false;

		bitmap = bp.SetBitmapAlpha(bitmap, alpha, bitmap.getWidth(),
				bitmap.getHeight());
		bp.saveBitmapPNG(parentPath + "background_alpha.png", bitmap);

		return true;
	}
	
	/**
	 * ��Ʈ�� ��ü ���������� ����
	 * 
	 * @param Dir
	 *            ��ü ���
	 * @return
	 */
	public int GET_LENGTH_SAVE_NOTE(String Dir) {
		File file = new File(Dir);
		String[] lists = file.list();
		Arrays.sort(lists, String.CASE_INSENSITIVE_ORDER);
		String lastindex = "1";
		if (lists.length > 1)
			lastindex = lists[lists.length - 2].substring(0, lists[lists.length - 2].indexOf("_"));
		Log.i("TAG", Integer.toString(lists.length - 2));
		return lists.length - 1;
	}

	/**
	 * ��Ʈ�� ��ü ���������� ����
	 * 
	 * @param Dir
	 *            ��ü ���
	 * @return
	 */
	public void DELETE_NOTE_PAGE(String Dir, int page) {
		File file = new File(Dir);
		String[] lists = file.list();
		Arrays.sort(lists, String.CASE_INSENSITIVE_ORDER);

		File delete = new File(Dir + Integer.toString(page) + "_note.png");
		delete.delete();

		for (int i = page; i < lists.length; i++) {
			File rename = new File(Dir + Integer.toString(i) + "_note.png");
			File target = new File(Dir + Integer.toString(i - 1) + "_note.png");
			rename.renameTo(target);
		}
	}
}
