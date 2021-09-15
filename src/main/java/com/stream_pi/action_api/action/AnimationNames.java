/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stream_pi.action_api.action;

public enum AnimationNames {
    NONE("None"),
    BOUNCE("Bounce"),
    BOUNCEINOUT("Bounce_InOut"),
    FADEINOUT("Fade_InOut"),
    FLASH("Flash"),
    FLIP("Flip"),
    JACKINBOX("Jack_In_The_Box"), 
    JELLO("Jello"),
    PULSE("Pulse"),
    ROLLINOUT("Roll_InOut"),
    ROTATEINOUT("Rotate_InOut"),
    RUBBER("RubberBand"),
    SHAKE("Shake_LeftRight"),
    SWING("Swing"),
    TADA("Tada"),
    WOBBLE("Wobble"),
    ZOOM("Zoom_InOut");
    
    private final String UIName;
    AnimationNames(String UIName)
    {
        this.UIName = UIName;
    }

    public String getUIName() {
        return UIName;
    }
}
