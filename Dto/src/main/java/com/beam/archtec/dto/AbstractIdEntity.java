package com.beam.archtec.dto;

/**
 * provide default hash code method and equals method
 *
 * @author bzhao024
 */
public abstract class AbstractIdEntity<I> implements IdEntity<I>{

    /**
     */
    private static final long serialVersionUID = 6001203600375396383L;

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IdEntity<?>)) {
            return false;
        }
        final IdEntity<?> other = (IdEntity<?>) obj;
        if (this.getId() == null) {
            return other.getId() == null;
        }
        return this.getId().equals(other.getId());
    }
    
}
