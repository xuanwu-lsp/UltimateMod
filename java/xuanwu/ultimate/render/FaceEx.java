package xuanwu.ultimate.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.model.obj.Face;
import net.minecraftforge.client.model.obj.TextureCoordinate;
import net.minecraftforge.client.model.obj.Vertex;

public class FaceEx
  extends Face
{
  public static boolean isSmoothShade = true;
  boolean hasVertexNormals = false;
  
  public FaceEx(Face face)
  {
    this.vertices = face.vertices;
    this.vertexNormals = face.vertexNormals;
    this.faceNormal = face.faceNormal;
    this.textureCoordinates = face.textureCoordinates;
    
    this.hasVertexNormals = (this.vertexNormals != null);
  }
  
  @SideOnly(Side.CLIENT)
  public void addFaceForRender(Tessellator tessellator, float textureOffset)
  {
    if ((!isSmoothShade) || (!this.hasVertexNormals)) {
      super.addFaceForRender(tessellator, textureOffset);
    }
    float averageU = 0.0F;
    float averageV = 0.0F;
    if ((this.textureCoordinates != null) && (this.textureCoordinates.length > 0))
    {
      for (int i = 0; i < this.textureCoordinates.length; i++)
      {
        averageU += this.textureCoordinates[i].u;
        averageV += this.textureCoordinates[i].v;
      }
      averageU /= this.textureCoordinates.length;
      averageV /= this.textureCoordinates.length;
    }
    for (int i = 0; i < this.vertices.length; i++)
    {
      tessellator.setNormal(this.vertexNormals[i].x, this.vertexNormals[i].y, this.vertexNormals[i].z);
      if ((this.textureCoordinates != null) && (this.textureCoordinates.length > 0))
      {
        float offsetU = textureOffset;
        float offsetV = textureOffset;
        if (this.textureCoordinates[i].u > averageU) {
          offsetU = -offsetU;
        }
        if (this.textureCoordinates[i].v > averageV) {
          offsetV = -offsetV;
        }
        tessellator.addVertexWithUV(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z, this.textureCoordinates[i].u + offsetU, this.textureCoordinates[i].v + offsetV);
      }
      else
      {
        tessellator.addVertex(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z);
      }
    }
  }
}
