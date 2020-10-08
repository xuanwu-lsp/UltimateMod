package xuanwu.ultimate.core;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.opengl.Display;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import net.minecraft.launchwrapper.LaunchClassLoader;
import xuanwu.ultimate.UltimateMod;
import xuanwu.ultimate.listeners.UltimateTimer;

public class Core implements IFMLLoadingPlugin{
public static boolean debug = true;
public static boolean CoreEnabled = false;
@Override
	public String[] getASMTransformerClass() {
		return new String[] {"xuanwu.ultimate.core.ClassFucker"};
	}
@Override
	public String getModContainerClass() {
return "xuanwu.ultimate.core.Container";
	}
@Override
	public String getSetupClass() {
		return null;
	}
@Override
	public void injectData(Map<String, Object> data) {
	UltimateMod.Check();
		debug = !((Boolean)data.get("runtimeDeobfuscationEnabled")).booleanValue();
		CoreEnabled = true;
	}
@Override
	public String getAccessTransformerClass() {
		return null;
	}

}
