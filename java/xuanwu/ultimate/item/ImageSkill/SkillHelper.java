package xuanwu.ultimate.item.ImageSkill;

public class SkillHelper{
public enum EnumSkillQuality{
Lowest,
Low,
Nomarl,
High,
Highest
}
public static String GetSkillQualityName(EnumSkillQuality quality) {
	switch(quality) {
	case High:
		return "稀世";
	case Highest:
		return "稀有";
	case Low:
		return "垃圾";
	case Lowest:
		return "废物";
	case Nomarl:
		return "普通";
	default:
	    return "未知";
	}
}
}