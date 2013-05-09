package mods.learncraft.common;

import java.sql.SQLException;

import mods.learncraft.commands.CommandTeamscore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.gui.achievement.*;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="MC_LearnCraft", name="LearnCraft", version="0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class Common {
	
    // The instance of your mod that Forge uses.
    @Instance("Common")
    public static Common instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="mods.learncraft.client.ClientProxy", serverSide="mods.learncraft.common.CommonProxy")
    public static CommonProxy proxy;
    
    public static int DiggerID;
    public static Digger Dig;
    
    public static int LCBlockID;
    public static Block LCBlock;
    
    public static int BlockBorderID;
    public static Block BorderBlock;
    
    public static int InvisibleBlockID;
    public static Block InvisibleBlock;
    
    public static DBQueries dbqueries = null;

    public static int LBlockChestID;
    public static LBlockChest lchest;
    
    public static int TeamChestID;
    public static TeamChest TeamChest;
    
    public static EntityPlayer[] playerlist = new EntityPlayer[100];

    public static int currentNumPlayers = 0;
    public static Team blueteam = new Team("blue");
    public static Team goldteam = new Team("gold");
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		System.out.println(config.toString());
		LCBlockID = config.getBlock("LCBlock", 501).getInt();
		LBlockChestID = config.getBlock("lchest", 502).getInt();
		BlockBorderID = config.getBlock("BorderBlock", 503).getInt();
		DiggerID = config.getBlock("Dig", 504).getInt();
		InvisibleBlockID = config.getBlock("InvisibleBlock", 505).getInt();
		TeamChestID = config.getBlock("TeamChest", 506).getInt();
		
		config.save();
    }
    
    @Init
    public void load(FMLInitializationEvent event) {
    	
    	LCBlock = (new LBlock(LCBlockID, Material.iron)).setUnlocalizedName("lcblock");
        LanguageRegistry.addName(LCBlock, "Learning block");
        GameRegistry.registerBlock(LCBlock, "lcblock");
        
        BorderBlock = (new BorderBlock(BlockBorderID, Material.iron)).setUnlocalizedName("border_block");
        LanguageRegistry.addName(BorderBlock, "Border Block");
        GameRegistry.registerBlock(BorderBlock, "border_block");

        lchest = (LBlockChest) (new LBlockChest(LBlockChestID, 0)).setUnlocalizedName("lc_chest");
        LanguageRegistry.addName(lchest, "Learning chest");
        GameRegistry.registerBlock(lchest, "lc_chest");
        GameRegistry.registerTileEntity(TileEntityLChest.class, "LChest.chest");
        
        Dig = (Digger) (new Digger(DiggerID)).setUnlocalizedName("digger");
        LanguageRegistry.addName(Dig, "Digger");
        GameRegistry.registerItem(Dig, "Digger");
        
        InvisibleBlock = new InvisibleBlock(InvisibleBlockID, Material.air, false).setUnlocalizedName("invisBlock");
        LanguageRegistry.addName(InvisibleBlock, "Invisible Block");
        GameRegistry.registerBlock(InvisibleBlock, "Invisible Block");
        
        TeamChest = (TeamChest) (new TeamChest(TeamChestID, 0)).setUnlocalizedName("teamchest");
        LanguageRegistry.addName(TeamChest, "TeamChest");
        GameRegistry.registerBlock(TeamChest, "teamchest");
        GameRegistry.registerTileEntity(TileEntityTeamChest.class, "TeamChest.chest");
        
        proxy.registerTileEntitySpecialRenderer();
    	proxy.registerRenderThings();
    	
    	// Add DBQueries to the Common handler
		try {
			dbqueries = new DBQueries();
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		
		MinecraftForge.EVENT_BUS.register(new EventHookContainerClass());
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
		
    	gameRegisters();
    	languageRegisters();
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event) {

    }
    
    @ServerStarting
    public void serverStart(FMLServerStartingEvent event)
    {
		MinecraftServer server = MinecraftServer.getServer(); //Gets current server
		ICommandManager command = server.getCommandManager(); //Gets the command manager to use for server
		ServerCommandManager serverCommand = ((ServerCommandManager) command); //Turns it into another form to use
		
		serverCommand.registerCommand(new CommandTeamscore());
    }
    
    private static void gameRegisters() {

    }
    
    private static void languageRegisters() {

    }

    public void registerRenderInformation()
    {

    }

    public void registerTileEntitySpecialRenderer()
    {

    }

	public static Team getTeam(EntityPlayer player) {
		// TODO Auto-generated method stub
		if(Common.blueteam.hasPlayer(player)) {
			return Common.blueteam;
		} else if(Common.goldteam.hasPlayer(player)) {
			return Common.goldteam;
		}
		return null;
	}

	public static void teleportPlayerTo(EntityPlayer player, String loc) {
		// TODO Auto-generated method stub
		if(loc.matches("choose_team")) {
			player.setPositionAndRotation(58.47, 123, 598.58, 0, -90);
		}
		if(loc.matches("gold_spawn")) {
			player.setPosition(207.96, 118, 698.06);
		}
		if(loc.matches("blue_spawn")) {
			player.setPosition(211, 116.1F, 499);
		}
		if(loc.matches("gold_arena")) {
			player.setPosition(117.17, 74, 548.35);
		}
		if(loc.matches("blue_arena")) {
			player.setPosition(299.42, 74, 649.98);
		}
		if(loc.matches("blue_arena")) {
			player.setPosition(117.17, 74, 548.35);
		}
		if(loc.matches("maze_spawn")) {
			player.setPositionAndRotation(264.65, 4.1, 464.49, 88, 219);
		}
	}

}
