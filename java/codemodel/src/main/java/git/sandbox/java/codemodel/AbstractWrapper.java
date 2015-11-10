package git.sandbox.java.codemodel;

public abstract class AbstractWrapper<WRAPPED> {

	private WRAPPED wrapped;

	public AbstractWrapper() {

	}

	public AbstractWrapper(WRAPPED wrapped) {
		setWrapped(wrapped);
	}

	protected WRAPPED getWrapped() {
		return wrapped;
	}

	public void setWrapped(WRAPPED wrapped) {
		this.wrapped = wrapped;
	}
}
