package mods.learncraft.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class TeamScoreDisplay extends Gui
{
	private Minecraft mc;
	private FontRenderer fontRender;
	private String bText, oText, bTextPlus, oTextPlus;
	private int renderTime;
	private boolean renderTimeCounting;
	public static boolean receivedPacket;
	public static int blueTeamScore, orangeTeamScore, orangeScorePlus, blueScorePlus;
	
	private static final int ORANGE_TEXT_X = 10;
	private static final int ORANGE_TEXT_Y = 10;
	private static final int ORANGE_TEXT_PLUS_Y = 10;
	private static final int ORANGE_TEXT_PLUS_X = 100;
	
	private static final int BLUE_TEXT_X = ORANGE_TEXT_X;
	private static final int BLUE_TEXT_Y = ORANGE_TEXT_Y + 13;
	private static final int BLUE_TEXT_PLUS_Y = BLUE_TEXT_Y;
	private static final int BLUE_TEXT_PLUS_X = 100;
	
	private static final int TEXTURED_RECT_X = ORANGE_TEXT_X - 7;
	private static final int TEXTURED_RECT_Y = ORANGE_TEXT_Y - 5;

	public TeamScoreDisplay(Minecraft mc)
	{
		super();

		// We need this to invoke the render engine.
		this.mc = mc;
		fontRender = mc.fontRenderer;
		
		blueTeamScore = 0;
		orangeTeamScore = 0;
		orangeScorePlus = 0;
		blueScorePlus = 0;
		renderTime = 0;
		
		renderTimeCounting = false;
		receivedPacket = false;
	}

	//
	// This event is called by GuiIngameForge during each frame by
	// GuiIngameForge.pre() and GuiIngameForce.post().
	//
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{

		// We draw after the ExperienceBar has drawn. The event raised by
		// GuiIngameForge.pre() will return true from isCancelable. If you call
		// event.setCanceled(true) in that case, the portion of rendering which this 
		// event represents will be canceled.
		// We want to draw *after* the experience bar is drawn, so we make sure
		// isCancelable() returns false and that the eventType represents the ExperienceBar event.
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{
			return;
		}
		
		mc.entityRenderer.setupOverlayRendering();

		//Update the text with the respective team points
		oText = "Orange Team: " + orangeTeamScore;
		bText = "Blue Team: " + blueTeamScore;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		//The new bind texture function, which in this case binds to the inventory texture
		this.mc.renderEngine.func_110577_a(new ResourceLocation("learncraft", "textures/gui/inventory.png"));

		//Draw a textured rectangle, using the arguments (xpos, ypos, xpos in texture, ypos in texture, width of
		//the "slice" of texture, height of the "slice" of texture)
		this.drawTexturedModalRect(TEXTURED_RECT_X, TEXTURED_RECT_Y, 0, 166, 150, 32);

		fontRender.drawStringWithShadow(oText, ORANGE_TEXT_X, ORANGE_TEXT_Y, 0xFF8400);
		fontRender.drawStringWithShadow(bText, BLUE_TEXT_X, BLUE_TEXT_Y, 0x009DFF);
		
		if(receivedPacket == true)
		{
			renderTime = 0;
			renderTimeCounting = true;
			receivedPacket = false;
		}
		
		if(renderTimeCounting == true && renderTime < 500)
		{
			renderTime++;
		}
		else if(renderTime == 500)
		{
			renderTime = 0;
			renderTimeCounting = false;
			orangeScorePlus = 0;
			blueScorePlus = 0;
		}
		
		if(orangeScorePlus != 0)
		{
			oTextPlus = "+" + orangeScorePlus;
			fontRender.drawStringWithShadow(oTextPlus, ORANGE_TEXT_PLUS_X, ORANGE_TEXT_PLUS_Y, 0xFF8400);
		}
		
		if(blueScorePlus != 0)
		{
			bTextPlus = "+" + blueScorePlus;
			fontRender.drawStringWithShadow(bTextPlus, BLUE_TEXT_PLUS_X, BLUE_TEXT_PLUS_Y, 0x009DFF);
		}
	}
}