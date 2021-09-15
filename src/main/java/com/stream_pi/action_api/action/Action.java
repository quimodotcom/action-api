/*
ActionAPI for StreamPi
Written by Debayan Sutradhar (dubbadhar)
 */

package com.stream_pi.action_api.action;

import com.stream_pi.action_api.actionproperty.ClientProperties;
import com.stream_pi.action_api.actionproperty.ServerProperties;
import com.stream_pi.util.exception.ActionNotFoundException;
import com.stream_pi.util.exception.ClientNotFoundException;
import com.stream_pi.util.exception.MinorException;
import com.stream_pi.util.exception.ProfileNotFoundException;
import com.stream_pi.util.version.Version;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.UUID;

public class Action implements Cloneable, Serializable
{
    private ActionType actionType=null;
    private String name ="Unknown action", ID=null;
    private double nameFontSize=-1;
    private Version version=new Version(0,0,0);

    private HashMap<String,byte[]> icons = null;
    private String currentIconState = "";

    public void setIcons(HashMap<String,byte[]> icons)
    {
        this.icons = icons;
    }

    public void addIcon(String state, byte[] icon)
    {
        if(icons==null)
            icons = new HashMap<>();

        icons.put(state, icon);
    }

    public byte[] getIcon(String state)
    {
        if(icons == null)
            return null;

        return icons.getOrDefault(state,null);
    }

    public void setNameFontSize(double nameFontSize)
    {
        this.nameFontSize = nameFontSize;
    }

    public double getNameFontSize()
    {
        return nameFontSize;
    }

    public void removeIcon(String state) throws MinorException
    {
        if(currentIconState.equals(state))
        {
            throw new MinorException("Cant change! Default icon state is set to "+state);
        }

        if(state.equals("default"))
        {
            if(actionType == ActionType.NORMAL ||
            actionType == ActionType.COMBINE ||
            actionType == ActionType.FOLDER)
            {
                throw new MinorException("Cant delete default icon!");
            }
        }

        if(state.equals("toggle_on") || state.equals("toggle_off"))
        {
            if(actionType == ActionType.TOGGLE)
            {
                throw new MinorException("Cant delete on/off icon!");
            }
        }


        icons.remove(state);
    }

    public HashMap<String,byte[]> getIcons()
    {
        if(icons == null)
            icons = new HashMap<>();

        return icons;
    }

    public String getCurrentIconState()
    {
        return currentIconState;
    }

    public byte[] getCurrentIcon()
    {
        return icons.get(currentIconState);
    }

    public void setCurrentIconState(String currentIconState)
    {
        this.currentIconState = currentIconState;
    }

    public boolean isHasIcon()
    {
        if(icons == null)
            return false;

        return icons.size() > 0;
    }


    private Location location = null;
    private String parent = null;

    private int delayBeforeExecuting = 0;

    public void setDelayBeforeExecuting(int delayBeforeExecuting)
    {
        this.delayBeforeExecuting = delayBeforeExecuting;
    }

    public int getDelayBeforeExecuting()
    {
        return delayBeforeExecuting;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return parent;
    }

    private String displayTextFontColourHex = null;
    private String bgColourHex = null;
    private String displayText = null;
    private boolean showDisplayText = true;
    private DisplayTextAlignment displayTextAlignment = DisplayTextAlignment.CENTER;
    private Animation actionAnimation = Animation.NONE;

    private Node serverButtonGraphic = null;

    private String helpLink = null;

    public String getHelpLink()
    {
        return helpLink;
    }

    public void setHelpLink(String helpLink)
    {
        this.helpLink = helpLink;
    }

    private ServerProperties serverProperties;
    private ClientProperties clientProperties;
    private String category;

    public String moduleName;

    public boolean invalid;

    public Action(String name, String ID, ActionType actionType)
    {
        this.serverProperties = new ServerProperties();
        this.clientProperties = new ClientProperties();
        this.name = name;
        setCategory("Others");
        this.ID = ID;
        this.actionType = actionType;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public void setShowDisplayText(boolean showDisplayText) {
        this.showDisplayText = showDisplayText;
    }

    public void setDisplayTextAlignment(DisplayTextAlignment displayTextAlignment) {
        this.displayTextAlignment = displayTextAlignment;
    }
    
    public void setActionAnimation(Animation actionAnimation) {
        this.actionAnimation = actionAnimation;
    }

    public void setDisplayTextFontColourHex(String displayTextFontColourHex) {
        this.displayTextFontColourHex = displayTextFontColourHex;
    }

    public String getDisplayTextFontColourHex() {
        return displayTextFontColourHex;
    }

    public void setBgColourHex(String bgColourHex) {
        this.bgColourHex = bgColourHex;
    }

    public String getBgColourHex() {
        return bgColourHex;
    }

    public DisplayTextAlignment getDisplayTextAlignment() {
        return displayTextAlignment;
    }
    
    public Animation getActionAnimation() {
        return actionAnimation;
    }

    public String getDisplayText() {
        return displayText;
    }

    public boolean isShowDisplayText()
    {
        return showDisplayText;
    }

    public void setServerButtonGraphic(String iconName)
    {
        try
        {
            this.serverButtonGraphic = new FontIcon(iconName);
        }
        catch (Exception e)
        {
            this.serverButtonGraphic = null;
        }
    }

    public void setServerButtonGraphic(ImageView graphic)
    {
        this.serverButtonGraphic = graphic;
    }

    public Node getServerButtonGraphic()
    {
        return serverButtonGraphic;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public Action(String ID, ActionType actionType)
    {
        this.serverProperties = new ServerProperties();
        this.clientProperties = new ClientProperties();
        this.category = "Others";
        this.ID = ID;
        this.actionType = actionType;
    }

    public Action(ActionType actionType)
    {
        this.serverProperties = new ServerProperties();
        this.clientProperties = new ClientProperties();
        setCategory("Others");
        this.actionType = actionType;
    }

    public Action()
    {
        this.serverProperties = new ServerProperties();
    }

    public void setIDRandom()
    {
        setID(UUID.randomUUID().toString());
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCategory()
    {
        return category;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getID()
    {
        return ID;
    }

    public void setClientProperties(ClientProperties properties)
    {
        this.clientProperties = properties;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Location getLocation()
    {
        return location;
    }

    public ServerProperties getServerProperties()
    {
        return serverProperties;
    }

    public ClientProperties getClientProperties()
    {
        return clientProperties;
    }

    public String getModuleName()
    {
        return moduleName;
    }


    public void setVersion(Version version)
    {
        this.version = version;
    }

    public Version getVersion()
    {
        return version;
    }

    public Action clone() throws CloneNotSupportedException {
        Action action = (Action) super.clone();
        action.setClientProperties(action.getClientProperties().clone());
        action.setIcons((HashMap<String, byte[]>) action.getIcons().clone());
        return action;
    }

    private ServerConnection serverConnection = null;

    public void setServerConnection(ServerConnection serverConnection)
    {
        this.serverConnection = serverConnection;
    }

    public ServerConnection getServerConnection()
    {
        return serverConnection;
    }

    private String profileID = null;

    public synchronized void setProfileID(String profileID)
    {
        this.profileID = profileID;
    }

    public synchronized String getProfileID()
    {
        return profileID;
    }

    private SocketAddress socketAddressForClient = null;

    public void setSocketAddressForClient(SocketAddress socketAddressForClient)
    {
        this.socketAddressForClient = socketAddressForClient;
    }

    public SocketAddress getSocketAddressForClient()
    {
        return socketAddressForClient;
    }


    private boolean currentToggleStatus = false;

    public void setCurrentToggleStatus(boolean currentToggleStatus)
    {
        this.currentToggleStatus = currentToggleStatus;
    }

    public boolean getCurrentToggleStatus()
    {
        return currentToggleStatus;
    }
}
