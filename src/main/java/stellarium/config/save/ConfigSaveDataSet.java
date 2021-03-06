package stellarium.config.save;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import stellarium.config.core.ConfigDataPhysicalManager;
import stellarium.config.core.StellarConfiguration;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class ConfigSaveDataSet extends WorldSavedData {
	
	private static final String key = "cfgdata.dat";
	private List<ConfigSaveData> dataList = Lists.newArrayList();
	
	public void loadConfigData(ConfigDataPhysicalManager manager, File location) throws IOException {
		File file = new File(location, key);
		
		if(!location.exists()) {
			location.createNewFile();
			manager.onFormatWithLogicalData();
			return;
		}
		
		manager.onFormatToLoad();
		
		FileInputStream fi = new FileInputStream(location);
		BufferedInputStream inp = new BufferedInputStream(fi);
		
		NBTTagCompound comp = CompressedStreamTools.readCompressed(inp);
		this.readFromNBT(comp);
		
		for(StellarConfiguration config : manager.getImmutableCfgList())
			dataList.add(new ConfigSaveData(config));
	}

	public void saveConfigData(File location) throws IOException {
		File file = new File(location, key);
		
		if(!location.exists())
			location.createNewFile();
				
		NBTTagCompound comp = new NBTTagCompound();
		this.writeToNBT(comp);

		FileOutputStream fo = new FileOutputStream(location);
		BufferedOutputStream outp = new BufferedOutputStream(fo);
		
		CompressedStreamTools.writeCompressed(comp, outp);
	}
	
	public ConfigSaveDataSet(ConfigDataPhysicalManager manager, File location) throws IOException {
		super(key);
		this.loadConfigData(manager, location);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		for(ConfigSaveData data : dataList) {
			data.readFromNBT(tag.getCompoundTag(data.getTitle()));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		for(ConfigSaveData data : dataList) {
			data.writeToNBT(tag.getCompoundTag(data.getTitle()));
		}
	}
	
}
