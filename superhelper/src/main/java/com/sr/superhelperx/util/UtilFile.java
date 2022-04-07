package com.sr.superhelperx.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by Hang.Yang on 2018/8/17 16:18.
 * If you find a path with no obstacles, it probably doesn’t lead anywhere.
 * In LongCaiHaErBin
 */

public final class UtilFile {
    private UtilFile() {
    }

    public static boolean createPathFile(String... filePaths) {
        boolean m = true;

        for(int i = 0; i < filePaths.length; ++i) {
            File f = new File(filePaths[i]);
            if(!f.exists()) {
                if(f.mkdirs()) {
                    Log.e("UtilFile", f.getPath() + "创建成功");
                } else {
                    Log.e("UtilFile", f.getPath() + "创建失败");
                    m = false;
                }
            } else {
                Log.e("UtilFile", f.getPath() + "已存在");
            }
        }
        return m;
    }

    public static void deletePathFile(String filePath) {
        (new File(filePath)).delete();
    }

    public static void copyFile(String paht, String newPath) {
        try {
            int e = 0;
            boolean bd = false;
            File of = new File(paht);
            if(of.exists()) {
                FileInputStream is = new FileInputStream(paht);
                FileOutputStream fos = new FileOutputStream(newPath);
                byte[] b = new byte[1024];

                int bd1;
                while((bd1 = is.read(b)) != -1) {
                    e += bd1;
                    System.out.println(e);
                    fos.write(b, 0, bd1);
                }

                fos.close();
                is.close();
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public static void zipFiles(File oldFile, File newFile) {
        try {
            ZipOutputStream e = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(newFile), 1048576));
            zipFile(oldFile, e, "");
            e.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath) {
        try {
            int e = 1048576;
            rootpath = rootpath + (rootpath.trim().length() == 0?"":File.separator) + resFile.getName();
            rootpath = new String(rootpath.getBytes("8859_1"), "GB2312");
            int r;
            if(resFile.isDirectory()) {
                File[] b = resFile.listFiles();
                File[] in = b;
                r = b.length;

                for(int var7 = 0; var7 < r; ++var7) {
                    File f = in[var7];
                    zipFile(f, zipout, rootpath);
                }
            } else {
                byte[] var10 = new byte[e];
                BufferedInputStream var11 = new BufferedInputStream(new FileInputStream(resFile), e);
                zipout.putNextEntry(new ZipEntry(rootpath));

                while((r = var11.read(var10)) != -1) {
                    zipout.write(var10, 0, r);
                }

                var11.close();
                zipout.flush();
                zipout.closeEntry();
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }

    public static void upZipFile(File zipFile, String folderPath) {
        try {
            File e = new File(folderPath);
            if(!e.exists()) {
                e.mkdirs();
            }

            ZipFile zf = new ZipFile(zipFile);
            Enumeration en = zf.entries();

            while(en.hasMoreElements()) {
                ZipEntry e1 = (ZipEntry)en.nextElement();
                InputStream in = zf.getInputStream(e1);
                String str = folderPath + File.separator + e1.getName();
                str = new String(str.getBytes("8859_1"), "GB2312");
                File f = new File(str);
                if(!f.exists()) {
                    File out = f.getParentFile();
                    if(!out.exists()) {
                        out.mkdirs();
                    }

                    f.createNewFile();
                }

                FileOutputStream out1 = new FileOutputStream(f);
                byte[] b = new byte[1048576];

                int r;
                while((r = in.read(b)) > 0) {
                    out1.write(b, 0, r);
                }

                in.close();
                out1.close();
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

    }

    private static void deleteFile(File file) {
        try {
            if(!file.exists()) {
                throw new Exception(file.getName());
            }

            if(file.isFile() && file.canWrite()) {
                file.delete();
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static boolean deleteAllFile(String path) {
        boolean f = false;
        File l = new File(path);
        if(!l.exists()) {
            return f;
        } else if(!l.isDirectory()) {
            return f;
        } else {
            String[] t = l.list();

            for(int i = 0; i < t.length; ++i) {
                File p;
                if(path.endsWith(File.separator)) {
                    p = new File(path + t[i]);
                } else {
                    p = new File(path + File.separator + t[i]);
                }

                if(p.isFile()) {
                    p.delete();
                }

                if(p.isDirectory()) {
                    deleteAllFile(path + "/" + t[i]);
                    deleteFolder(path + "/" + t[i]);
                    f = true;
                }
            }

            return f;
        }
    }

    public static void deleteFolder(String folderPath) {
        try {
            deleteFolder(folderPath);
            (new File(folderPath)).delete();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs();
            File e = new File(oldPath);
            String[] f = e.list();
            File t = null;

            for(int i = 0; i < f.length; ++i) {
                if(oldPath.endsWith(File.separator)) {
                    t = new File(oldPath + f[i]);
                } else {
                    t = new File(oldPath + File.separator + f[i]);
                }

                if(t.isFile()) {
                    FileInputStream fis = new FileInputStream(t);
                    FileOutputStream fos = new FileOutputStream(newPath + "/" + t.getName().toString());
                    byte[] b = new byte[5120];

                    int len;
                    while((len = fis.read(b)) != -1) {
                        fos.write(b, 0, len);
                    }

                    fos.flush();
                    fos.close();
                    fis.close();
                }

                if(t.isDirectory()) {
                    copyFolder(oldPath + "/" + f[i], newPath + "/" + f[i]);
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    public static List<File> getFileSort(String path) {
        List l = getFiles(path, new ArrayList());
        if(l != null && l.size() > 0) {
            Collections.sort(l, new Comparator() {
                @Override
                public int compare(Object f, Object nf) {
//                    return 0;
                    return ((File)f).lastModified() < ((File)nf).lastModified()?1:(((File)f).lastModified() == ((File)nf).lastModified()?0:-1);
                }

//                public int compare(File f, File nf) {
//                    return f.lastModified() < nf.lastModified()?1:(f.lastModified() == nf.lastModified()?0:-1);
//                }
            });
        }

        return l;
    }

    private static List<File> getFiles(String realpath, List<File> files) {
        File r = new File(realpath);
        if(r.isDirectory()) {
            File[] var3 = r.listFiles();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                File f = var3[var5];
                if(f.isDirectory()) {
                    getFiles(f.getAbsolutePath(), files);
                } else {
                    files.add(f);
                }
            }
        }

        return files;
    }
}
