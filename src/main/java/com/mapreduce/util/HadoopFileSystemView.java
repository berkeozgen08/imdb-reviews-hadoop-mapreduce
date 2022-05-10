package com.mapreduce.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

import com.mapreduce.Singletons;

import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.RemoteIterator;

public class HadoopFileSystemView extends FileSystemView {
	final Path base;
	
	public HadoopFileSystemView(final Path base) {
		this.base = base;
	}

	@Override
	protected File createFileSystemRoot(File f) {
		return new HadoopFile(f);
	}

	@Override
	public boolean isComputerNode(File dir) {
		return false;
	}

	@Override
	public boolean isFloppyDrive(File dir) {
		return false;
	}

	@Override
	public boolean isDrive(File dir) {
		return false;
	}

	@Override
	public Icon getSystemIcon(File f) {
		return null;
	}

	@Override
	public String getSystemTypeDescription(File f) {
		return f.toPath().toString();
	}

	@Override
	public String getSystemDisplayName(File f) {
		return f.getName();
	}

	@Override
	public File getParentDirectory(final File dir) {
		return new HadoopFile(dir.getParentFile());
	}

	@Override
	public File[] getFiles(final File dir, boolean useFileHiding) {
		
		try {
			final List<File> files = new ArrayList<>();
			RemoteIterator<LocatedFileStatus> iter =
				Singletons.fileSystem.listFiles(new org.apache.hadoop.fs.Path(dir.getPath()), false);
			while (iter.hasNext()) {
				files.add(new HadoopFile(iter.next().getPath().toString()));
			}
			return files.toArray(new File[files.size()]);
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}

		return new File[0];
	}

	@Override
	public File createFileObject(final String path) {
		return new HadoopFile(path);
	}

	@Override
	public File createFileObject(final File dir, final String filename) {
		Path fileObject;

		if (dir != null) {
			fileObject = Paths.get(dir.toPath().toString(), filename);
		} else {
			fileObject = Paths.get(filename);
		}
		return new HadoopFile(fileObject.toFile());
	}

	@Override
	public File getDefaultDirectory() {
		return new HadoopFile(base.toFile());
	}

	@Override
	public File getHomeDirectory() {
		return new HadoopFile(base.toFile());
	}

	@Override
	public File[] getRoots() {
		return new File[] {new HadoopFile("/")};
	}

	@Override
	public boolean isFileSystemRoot(final File dir) {
		boolean isRoot = dir.toPath().getParent() == null;
		return isRoot;
	}

	@Override
	public boolean isHiddenFile(final File f) {
		return false;
	}

	@Override
	public boolean isFileSystem(final File f) {
		return !isFileSystemRoot(f);
	}

	@Override
	public File getChild(final File parent, final String fileName) {
		return new HadoopFile(parent, fileName);
	}

	@Override
	public boolean isParent(final File folder, final File file) {
		return file.toPath().getParent().equals(folder.toPath());
	}

	@Override
	public Boolean isTraversable(final File f) {
		try {
			return Singletons.fileSystem.isDirectory(new org.apache.hadoop.fs.Path(f.getPath()));
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isRoot(final File f) {
		return f.getPath().equals("/");
	}

	@Override
	public File createNewFolder(final File containingDir) throws IOException {
		return new HadoopFile(containingDir);
	}

	private class HadoopFile extends File {
		private static final long serialVersionUID = -1752685357864733168L;

		private HadoopFile(final File file) {
			super(file.toString());
		}

		private HadoopFile(String pathname) {
			super(pathname);
		}

		private HadoopFile(String parent, String child) {
			super(parent, child);
		}

		private HadoopFile(File parent, String child) {
			super(parent, child);
		}

		@Override
		public boolean exists() {
			return true;
		}

		@Override
		public boolean isDirectory() {
			return HadoopFileSystemView.this.isTraversable(this);
		}

		@Override
		public File getCanonicalFile() throws IOException {
			return new HadoopFile(super.getCanonicalFile());
		}

		@Override
		public File getAbsoluteFile() {
			return new HadoopFile(super.getAbsoluteFile());
		}

		@Override
		public File getParentFile() {
			File parent = super.getParentFile();

			if (parent != null) {
				parent = new HadoopFile(super.getParentFile());
			}
			return parent;
		}

	}

}