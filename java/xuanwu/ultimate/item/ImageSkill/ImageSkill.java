package xuanwu.ultimate.item.ImageSkill;

import java.util.ArrayList;
import java.util.List;

import scala.actors.threadpool.Arrays;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.item.ImageSkill.SkillHelper.EnumSkillQuality;

public class ImageSkill {
public static final List<ImageSkill> skills = new ArrayList<ImageSkill>();
protected List<String> SkillDiscribe;
protected EnumSkillQuality Quality;
public String[] GetSkillDiscribe() {
	List<String> RetList = new ArrayList<String>();
	for(String str : this.SkillDiscribe) {
		RetList.add(LudicrousText.makeFabulous(str));
	}
	return (String[])RetList.toArray();
}
public ImageSkill(List<String> SkillDiscribe,EnumSkillQuality quality) {
	this.Quality = quality;
	this.SkillDiscribe = SkillDiscribe;
	ImageSkill.skills.add(this);
}
public ImageSkill(String[] SkillDiscribe,EnumSkillQuality quality) {
	this(Arrays.asList(SkillDiscribe),quality);
}
}
