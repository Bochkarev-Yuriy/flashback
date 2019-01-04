package ru.coc.flashback.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yuriy Bochkarev
 * @since 02.01.2019.
 */

@Entity
@Table(name = "sequences", uniqueConstraints = {@UniqueConstraint(columnNames = {"seq_name"})})
public class Sequences {

    @Id
    @Column(name = "seq_name", length = 50, nullable = false)
    private String seqName;

    @Column(name = "seq_count")
    private Long seqCount;


    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public Long getSeqCount() {
        return seqCount;
    }

    public void setSeqCount(Long seqCount) {
        this.seqCount = seqCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sequences sequences = (Sequences) o;
        return Objects.equals(seqName, sequences.seqName) &&
                Objects.equals(seqCount, sequences.seqCount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(seqName, seqCount);
    }

    @Override
    public String toString() {
        return "Sequences{" +
                "seqName='" + seqName + '\'' +
                ", seqCount=" + seqCount +
                '}';
    }
}
