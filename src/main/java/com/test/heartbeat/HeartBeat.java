package com.test.heartbeat;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeartBeat implements Serializable {
    private long time;
}
