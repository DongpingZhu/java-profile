package com.test.format;

public enum EntityR {
    TR1(0, "T1", "R1"),
    TR2(1, "T2", "R2"),
    TR3(2, "T3", "R3"),
    TR4(3, "T4", "R4"),
    TR5(4, "T5", "R5"),
    TR6(5, "T6", "R6"),
    TR7(6, "T7", "R7");
    private int index;
    private String itemE;
    private String itemR;

    EntityR(int index, String itemE, String itemR) {
        this.index = index;
        this.itemE = itemE;
        this.itemR = itemR;
    }

    public static String getItemEByIndex(int index) {
        for (EntityR e : EntityR.values()) {
            if (e.index == index) {
                return e.itemE;
            }
        }
        return null;
    }

    public static String getItemRByIndex(int index) {
        for (EntityR e : EntityR.values()) {
            if (e.index == index) {
                return e.itemR;
            }
        }
        return null;
    }
}
