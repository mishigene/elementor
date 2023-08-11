package io.elementor.infra.tearDown;

abstract public class TearDownTaskWrapper {
    private TearDownTask task;

    /**
     * do NOT use this constructor to instantiate a new object from this class.
     */
    public TearDownTaskWrapper() {
    }

    public TearDownTaskWrapper(TearDownTask task) {
        this.task = task;
    }

    public TearDownTask getTask() {
        return task;
    }

    public void setTask(TearDownTask task) {
        this.task = task;
    }

    public abstract void invoke();
}
