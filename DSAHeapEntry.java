public class DSAHeapEntry {
    private int m_priority;
    private Object m_value;

    //region accessor
    public int getPriority() {
        return m_priority;
    }

    public Object getValue() {
        return m_value;
    }
    //endregion

    //region mutator
    public void setPriority(int prio) {
        m_priority = prio;
    }

    public void setValue(Object val) {
        m_value = val;
    }
    //endregion
}