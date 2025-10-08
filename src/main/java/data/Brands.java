package data;

import lombok.Getter;

@Getter
public enum Brands {
    Polo("Polo"),
    HM("H&M"),
    MADAME("MADAME"),
    MAST_HARBOUR("MAST & HARBOUR"),
    BABYHUG("BABYHUG"),
    ALLEN_SOLLY_JUNIOR("ALLEN SOLLY JUNIOR"),
    KOOKIE_KIDS("KOOKIE KIDS"),
    BIBA("BIBA");

    /**
     * -- GETTER --
     *  Gets the exact string value of the brand name as it appears on the website.
     */
    private final String brandName;

    Brands(String brandName) {
        this.brandName = brandName;
    }

}
