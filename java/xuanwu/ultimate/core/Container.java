package xuanwu.ultimate.core;

import java.io.File;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.MetadataCollection;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.ModContainer.Disableable;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.VersionRange;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import xuanwu.ultimate.UltimateMod;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.Subscribe;

@TransformerExclusions(value = {"xuanwu.ultimate.core.ClassFucker","xuanwu.ultimate.core.util.EventUtil","xuanwu.ultimate.core.ultimateCore","xuanwu.ultimate.listeners.*","xuanwu.ultimate.item.ultimateSword"})
@MCVersion(value = "1.7.10")
@Name(value = "UltimateCore")
@SortingIndex(value = Integer.MIN_VALUE)
public class Container
extends DummyModContainer
implements ModContainer
{
	private Logger log;
	public static final String MODID = "UltimateCore";
	public static ModMetadata meta;
	private boolean enabled = true;
	public Container()
	{
	super(new ModMetadata());
	meta = getMetadata();
	meta.modId = MODID;
	meta.name = "UltimateCore";
	meta.version = UltimateMod.VERSION;
	meta.authorList = Arrays.asList("玄武i寒舞");
	meta.description = "";
	meta.url = "https://wdnmdcnmdnmsl-1300078504.cos.ap-chengdu.myqcloud.com/https/1.html";
	}
	public boolean registerBus(EventBus bus, LoadController controller)
	{
	bus.register(this);
	return true;
	}
	@Subscribe
	  public void preInit(FMLPreInitializationEvent event)
	  {
	    this.log = event.getModLog();
	  }
    @Override
    public Disableable canBeDisabled()
    {
        return Disableable.NEVER;
    }
	  @Subscribe
	  public void init(FMLInitializationEvent event)
	  {
	    this.log.info("DEBUG" + Core.debug);
	  }
	  @Override
	    public void setEnabledState(boolean enabled) {
	        this.enabled = true;
	    }
}
