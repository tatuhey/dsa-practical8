public class DSAHeapEntry {
    private int m_priority;
    private Object m_value;

    //region constructor
    public DSAHeapEntry(int prio, Object val) {
        m_priority = prio;
        m_value = val;
    }

    public DSAHeapEntry(){
        m_priority = 0;
        m_value = null;
    }
    //endregion

    //region accessor
    public int getPriority() {
        return m_priority;
    }

    public Object getValue() {
        return m_value;
    }

    @Override
    public String toString() {
        return "Priority: " + m_priority + ", Value: " + m_value;
    }
    //endregion

    //region mutator
    public void setPriority(int prio) {
        m_priority = prio;
    }

    public void setValue(Object val) {
        m_value = val;
    }

    public void setAll(int prio, Object val) {
        setPriority(prio);
        setValue(val);
    }
    //endregion
}