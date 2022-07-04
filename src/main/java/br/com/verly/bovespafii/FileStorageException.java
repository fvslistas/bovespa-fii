package br.com.verly.bovespafii;

public class FileStorageException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileStorageException(String msg, Throwable t) {
		super(msg, t);
	}

	public FileStorageException(String msg) {
		super(msg);
	}
}
