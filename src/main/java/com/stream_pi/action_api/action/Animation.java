/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stream_pi.action_api.action;

public enum Animation {
    NONE("None"),
    BOUNCE("Bounce"),
    BOUNCEINOUT("Bounce In/Out"),
    FADEINOUT("Fade In/Out"),
    FLASH("Flash"),
    FLIP("Flip"),
    JACKINBOX("Jack In The Box"), 
    JELLO("Jello"),
    PULSE("Pulse"),
    ROLLINOUT("Roll In/Out"),
    ROTATEINOUT("RotateIn/Out"),
    RUBBER("RubberBand"),
    SHAKE("Shake Left/Right"),
    SWING("Swing"),
    TADA("Tada"),
    WOBBLE("Wobble"),
    ZOOM("Zoom In/Out");
    
    private final String UIName;
    Animation(String UIName)
    {
        this.UIName = UIName;
    }

    public String getUIName() {
        return UIName;
    }
}
