package xuanwu.ultimate.core;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import scala.tools.asm.tree.ClassNode;
import scala.tools.asm.tree.MethodNode;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassFucker
  implements IClassTransformer
{
	/**
	 * Unknow bug,please fix it!
	 * Make UltimateDead_Transformd a static final constant or non-public and provide accessors if needed.
	 */
	  public static boolean UltimateDead_Transformd = false;
	  /**
	   * if you want to copy code,you found right place!
	   * You should copy ClassFucker.java and Container.java and EventUtil.java at xuanwu.ultimate.core.util
	   * 当然,如果你看不懂的话,直接复制吧。
	   */
  public byte[] transform(String name,String transformedName, byte[] basicClass)
  {
		  if(name.equals("net.minecraft.entity.player.EntityPlayer") || name.equals("yz")) {
		      ClassReader classReader = new ClassReader(basicClass);
		      ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS);
		      ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, classWriter)
		      {
		    	  public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
			        {
		    		  if(name.equals("getAIMoveSpeed") || name.equals("bl") && desc.equals("()F")) {
				            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
				            mv.visitCode();
				            Label start = new Label();
				            mv.visitLabel(start);
				            mv.visitVarInsn(Opcodes.ALOAD, 0);
				            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "getAIMoveSpeed", "(Lnet/minecraft/entity/player/EntityPlayer;)F", false);
				            mv.visitInsn(Opcodes.FRETURN);
				            Label end = new Label();
				            mv.visitLabel(end);
				            mv.visitLocalVariable("this", "Lnet/minecraft/entity/player/EntityPlayer;", null, start, end, 0);
				            mv.visitMaxs(1, 1);
				            mv.visitEnd();
				            return null;
		    		  }
		    		  if(name.equals("isPushedByWater") || name.equals("aC") && desc.equals("()Z")) {
				            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
				            mv.visitCode();
				            Label start = new Label();
				            mv.visitLabel(start);
				            mv.visitVarInsn(Opcodes.ALOAD, 0);
				            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "isPushedByWater", "(Lnet/minecraft/entity/player/EntityPlayer;)Z", false);
				            mv.visitInsn(Opcodes.IRETURN);
				            Label end = new Label();
				            mv.visitLabel(end);
				            mv.visitLocalVariable("this", "Lnet/minecraft/entity/player/EntityPlayer;", null, start, end, 0);
				            mv.visitMaxs(1, 1);
				            mv.visitEnd();
				            return null;
		    		  }
		    		  if(name.equals("canEat") || name.equals("g") && desc.equals("(Z)Z")) {
				            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
				            mv.visitCode();
				            Label start = new Label();
				            mv.visitLabel(start);
				            mv.visitVarInsn(Opcodes.ALOAD, 0);
				            mv.visitVarInsn(Opcodes.ILOAD, 1);
				            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "canEat", "(Lnet/minecraft/entity/player/EntityPlayer;Z)Z", false);
				            mv.visitInsn(Opcodes.IRETURN);
				            Label end = new Label();
				            mv.visitLabel(end);
				            mv.visitLocalVariable("this", "Lnet/minecraft/entity/player/EntityPlayer;", null, start, end, 0);
				            mv.visitLocalVariable("p_71043_1_", "Z", null, start, end, 0);
				            mv.visitMaxs(2, 2);
				            mv.visitEnd();
				            return null;
		    		  }
		    		  return this.cv.visitMethod(access, name, desc, signature, exceptions);
			        }
		      };
		        classReader.accept(classVisitor, Opcodes.ASM4);
		        return classWriter.toByteArray();
		  }
		  if(name.equals("net.minecraft.entity.Entity") || name.equals("sa")) {
		      ClassReader classReader = new ClassReader(basicClass);
		      ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS);
		      ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, classWriter)
		      {
			  public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
		        {
				  super.visit(version, access,name, signature, superName, interfaces);
				  if(!UltimateDead_Transformd) {
				  FieldVisitor UltimateDead = cv.visitField(Opcodes.ACC_PUBLIC,"UltimateDead","Z",null,null);
				  UltimateDead.visitEnd();
				  UltimateDead_Transformd = true;
				  }
		        }
		        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		        	if(name.equals("isInWeb") || name.equals("I")
		        			|| name.equals("nextStepDistance") || name.equals("d")
		        ||( name.equals("fire") || name.equals("e")) ||
		        (name.equals("rand") || name.equals("Z"))) {
		        		access = Opcodes.ACC_PUBLIC;
		        	}
		        	            return this.cv.visitField(access, name, desc, signature, value);
		        	          }
			  public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
		        {
				  if(name.equals("func_145775_I") || name.equals("I")) {
					  access = Opcodes.ACC_PUBLIC;
				  }
		        	if (name.equals("moveEntity") || name.equals("d") && desc.equals("(DDD)V"))
			          {
			            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
			            mv.visitCode();
			            Label start = new Label();
			            mv.visitLabel(start);
			            mv.visitVarInsn(Opcodes.ALOAD, 0);
			            mv.visitVarInsn(Opcodes.DLOAD, 1);
			            mv.visitVarInsn(Opcodes.DLOAD, 3);
			            mv.visitVarInsn(Opcodes.DLOAD, 5);
			            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "moveEntity", "(Lnet/minecraft/entity/Entity;DDD)V", false);
			            mv.visitInsn(Opcodes.RETURN);
			            Label end = new Label();
			            mv.visitLabel(end);
			            mv.visitLocalVariable("this", "Lnet/minecraft/entity/Entity;", null, start, end, 0);
			            mv.visitLocalVariable("p_70091_1_", "D", null, start, end, 1);
			            mv.visitLocalVariable("p_70091_3_", "D", null, start, end, 1);
			            mv.visitLocalVariable("p_70091_5_", "D", null, start, end, 1);
			            mv.visitMaxs(4, 4);
			            mv.visitEnd();
			            return null;
			          }
				  return this.cv.visitMethod(access, name, desc, signature, exceptions);
		        }
		      };
		        classReader.accept(classVisitor, Opcodes.ASM4);
		        return classWriter.toByteArray();
		  }
	  if (name.equals("net.minecraftforge.common.ForgeHooks"))
	    {
	      ClassReader classReader = new ClassReader(basicClass);
	      ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS);
	      ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, classWriter)
	      {
	        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	        {
	        	if (name.equals("onLivingAttack"))
		          {
		            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
		            mv.visitCode();
		            Label start = new Label();
		            mv.visitLabel(start);
		            mv.visitVarInsn(Opcodes.ALOAD, 0);
		            mv.visitVarInsn(Opcodes.ALOAD, 1);
		            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "onLivingAttack", "(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/util/DamageSource;)Z", false);
		            mv.visitInsn(Opcodes.IRETURN);
		            Label end = new Label();
		            mv.visitLabel(end);
		            mv.visitLocalVariable("entity", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
		            mv.visitLocalVariable("src", "Lnet/minecraft/util/DamageSource;", null, start, end, 1);
		            mv.visitMaxs(2, 2);
		            mv.visitEnd();
		            return null;
		          }
	          if (name.equals("onLivingDeath"))
	          {
	            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	            mv.visitCode();
	            Label start = new Label();
	            mv.visitLabel(start);
	            mv.visitVarInsn(Opcodes.ALOAD, 0);
	            mv.visitVarInsn(Opcodes.ALOAD, 1);
	            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "onLivingDeath", "(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/util/DamageSource;)Z", false);
	            
	            mv.visitInsn(172);
	            Label end = new Label();
	            mv.visitLabel(end);
	            mv.visitLocalVariable("entity", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
	            mv.visitLocalVariable("src", "Lnet/minecraft/util/DamageSource;", null, start, end, 1);
	            mv.visitMaxs(2, 2);
	            mv.visitEnd();
	            return null;
	          }
	          if (name.equals("onLivingUpdate"))
	          {
	            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	            mv.visitCode();
	            Label start = new Label();
	            mv.visitLabel(start);
	            mv.visitVarInsn(Opcodes.ALOAD, 0);
	            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "onLivingUpdate", "(Lnet/minecraft/entity/EntityLivingBase;)Z", false);
	            
	            mv.visitInsn(172);
	            Label end = new Label();
	            mv.visitLabel(end);
	            mv.visitLocalVariable("entity", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
	            mv.visitMaxs(1, 1);
	            mv.visitEnd();
	            return null;
	          }
	          return this.cv.visitMethod(access, name, desc, signature, exceptions);
	        }
	      };
	      classReader.accept(classVisitor, Opcodes.ASM4);
	      return classWriter.toByteArray();
	    }
if(name.equals("mq") || name.equals("net.minecraft.server.management.PlayerManager")) {
    ClassReader classReader = new ClassReader(basicClass);
    ClassWriter classWriter = new ClassWriter(classReader, 1);
    ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, (ClassVisitor)classWriter) {
        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
if(name.equals("players") || name.equals("c")) {
	access = Opcodes.ACC_PUBLIC;
}
            return this.cv.visitField(access, name, desc, signature, value);
          }
    };
    classReader.accept(classVisitor, Opcodes.ASM4);
    return classWriter.toByteArray();
}
	    if (name.equals("bao") || name.equals("net.minecraft.client.Minecraft")) {
	        ClassReader classReader = new ClassReader(basicClass);
	        ClassWriter classWriter = new ClassWriter(classReader, 1);
	        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, (ClassVisitor)classWriter) {
	            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
	            	if(access == Opcodes.ACC_PRIVATE || access == Opcodes.ACC_PROTECTED) {
	            	access = Opcodes.ACC_PUBLIC;
	            	}else {
	      if((access & Opcodes.ACC_STATIC) == 0) {
	            		access = Opcodes.ACC_PUBLIC;
	      }
	            	}
	              return this.cv.visitField(access, name, desc, signature, value);
	            }
	            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
	              if ((name.equals("p") && desc.equals("()V")) || name.equals("runTick")) {
	                return new MethodVisitor(Opcodes.ASM4, this.cv.visitMethod(access, name, desc, signature, exceptions)) {
	                    public void visitCode() {
	                      this.mv.visitVarInsn(Opcodes.ALOAD, 0);
	                      this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "runTick", "(Lnet/minecraft/client/Minecraft;)V", false);
	                    }
	                  };
	              }
		          if (name.equals("ag") && desc.equals("()V") || name.equals("startGame"))
		          {
		            MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
		            mv.visitCode();
		            Label start = new Label();
		            mv.visitLabel(start);
		            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "startGame", "()V", false);
		            
		            mv.visitInsn(Opcodes.RETURN);
		            Label end = new Label();
		            mv.visitLabel(end);
		            mv.visitEnd();
		            return null;
		          }
	              if ((name.equals("ak") && desc.equals("()V")) || name.equals("runGameLoop")) {
	                  MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	                  mv.visitCode();
	                  Label start = new Label();
	                  mv.visitLabel(start);
	                  mv.visitVarInsn(Opcodes.ALOAD, 0);
	                  mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "runGameLoop", "(Lnet/minecraft/client/Minecraft;)V", false);
	                  
	                  mv.visitInsn(Opcodes.RETURN);
	                  Label end = new Label();
	                  mv.visitLabel(end);
	                  mv.visitLocalVariable("this", "Lnet/minecraft/client/Minecraft;", null, start, end, 0);
	                  mv.visitMaxs(1, 1);
	                  mv.visitEnd();
	                  return null;
	              } 
	                return this.cv.visitMethod(access,name, desc, signature, exceptions);
	            }
	          };
	        classReader.accept(classVisitor, Opcodes.ASM4);
	        return classWriter.toByteArray();
	      } 
	    if(name.equals("ahb") || name.equals("net.minecraft.world.World")){
	        ClassReader classReader = new ClassReader(basicClass);
	        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
	        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, classWriter)
	        {
	            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
if(name.equals("addedTileEntityList") || name.equals("a")) {
	access = Opcodes.ACC_PUBLIC;
}
if(name.equals("field_147483_b") || name.equals("b")) {
	access = Opcodes.ACC_PUBLIC;
}
if(name.equals("field_147481_N") || name.equals("M")) {
	access = Opcodes.ACC_PUBLIC;
}
if(name.equals("unloadedEntityList") || name.equals("f")) {
	access = Opcodes.ACC_PUBLIC;
}
if(name.equals("worldAccesses") || name.equals("u")) {
	access = Opcodes.ACC_PUBLIC;
}
if(name.equals("chunkProvider") || name.equals("v")) {
	access = Opcodes.ACC_PUBLIC;
}
		              return this.cv.visitField(access, name, desc, signature, value);
		            }
	          public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	          {
	        	  if(name.equals("updateEntities") || name.equals("h") && desc.equals("()V")){
	                  MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	                  mv.visitCode();
	                  Label start = new Label();
	                  mv.visitLabel(start);
	                  mv.visitVarInsn(Opcodes.ALOAD, 0);
	                  mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "updateEntities", "(Lnet/minecraft/world/World;)V", false);
	                  
	                  mv.visitInsn(Opcodes.RETURN);
	                  Label end = new Label();
	                  mv.visitLabel(end);
	                  mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", null, start, end, 0);
	                  mv.visitMaxs(1, 1);
	                  mv.visitEnd();
	                  return null;
	        	  }
	        	  if(name.equals("onEntityRemoved") || (name.equals("b") && desc.equals("(Lsa;)V"))) {
	                  MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	                  mv.visitCode();
	                  Label start = new Label();
	                  mv.visitLabel(start);
	                  mv.visitVarInsn(Opcodes.ALOAD, 0);
	                  mv.visitVarInsn(Opcodes.ALOAD, 1);
	                  mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "onEntityRemoved", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);
	                  
	                  mv.visitInsn(Opcodes.RETURN);
	                  Label end = new Label();
	                  mv.visitLabel(end);
	                  mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", null, start, end, 0);
	                  
	                  mv.visitLocalVariable("p_72847_1_","Lnet/minecraft/entity/Entity;", null, start, end,1);
	                  mv.visitMaxs(2, 2);
	                  mv.visitEnd();
	                  return null; 
	        	  }
	        	  if(name.equals("removePlayerEntityDangerously") || (name.equals("f") && desc.equals("(Lsa;)V"))) {
	                  MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	                  mv.visitCode();
	                  Label start = new Label();
	                  mv.visitLabel(start);
	                  mv.visitVarInsn(Opcodes.ALOAD, 0);
	                  mv.visitVarInsn(Opcodes.ALOAD, 1);
	                  mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "removePlayerEntityDangerously", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);

	                  mv.visitInsn(Opcodes.RETURN);
	                  Label end = new Label();
	                  mv.visitLabel(end);
	                  mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", null, start, end, 0);
	                  mv.visitLocalVariable("p_72973_1","Lnet/minecraft/entity/Entity;", null, start, end,1);
	                  mv.visitMaxs(2, 2);
	                  mv.visitEnd();
	                  return null; 
	        	  }
	        	  if(name.equals("removeEntity") || (name.equals("e") && desc.equals("(Lsa;)V"))) {
	                  MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	                  mv.visitCode();
	                  Label start = new Label();
	                  mv.visitLabel(start);
	                  mv.visitVarInsn(Opcodes.ALOAD, 0);
	                  mv.visitVarInsn(Opcodes.ALOAD, 1);
	                  mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "removeEntity", "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)V", false);
	                  
	                  mv.visitInsn(Opcodes.RETURN);
	                  Label end = new Label();
	                  mv.visitLabel(end);
	                  mv.visitLocalVariable("this", "Lnet/minecraft/world/World;", null, start, end, 0);
	                  mv.visitLocalVariable("p_72900_1_","Lnet/minecraft/entity/Entity;", null, start, end,1);
	                  mv.visitMaxs(2, 2);
	                  mv.visitEnd();
	                  return null; 
	        	  }
	        	  return this.cv.visitMethod(access, name, desc, signature, exceptions);
	          }
	        };
	        classReader.accept(classVisitor, Opcodes.ASM4);
	        return classWriter.toByteArray();
	    }
	  if ((name.equals("nh")) || (name.equals("net.minecraft.network.NetHandlerPlayServer")))
      {
        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, classWriter)
        {
          public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
          {
            if (((name.equals("c")) && (desc.equals("(Ljava/lang/String;)V"))) || 
              (name.equals("kickPlayerFromServer")))
            {
              MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
              mv.visitCode();
              Label start = new Label();
              mv.visitLabel(start);
              mv.visitVarInsn(Opcodes.ALOAD, 0);
              mv.visitVarInsn(Opcodes.ALOAD, 1);
              mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "kickPlayerFromServer", "(Lnet/minecraft/network/NetHandlerPlayServer;Ljava/lang/String;)V", false);
              
              mv.visitInsn(Opcodes.RETURN);
              Label end = new Label();
              mv.visitLabel(end);
              mv.visitLocalVariable("this", "Lnet/minecraft/network/NetHandlerPlayServer;", null, start, end, 0);
              
              mv.visitLocalVariable("message", "Ljava/lang/String;", null, start, end, 1);
              mv.visitMaxs(2, 2);
              mv.visitEnd();
              return null;
            }
            return this.cv.visitMethod(access, name, desc, signature, exceptions);
          }
        };
        classReader.accept(classVisitor, Opcodes.ASM4);
        return classWriter.toByteArray();
      }
	    if ((name.equals("sv")) || (name.equals("net.minecraft.entity.EntityLivingBase")))
	    {
	      ClassReader classReader = new ClassReader(basicClass);
	      ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
	      ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM4, classWriter)
	      {
	        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	        {
	          super.visit(version, access, name, signature, superName, interfaces);
	          MethodVisitor mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, Core.debug ? "getHealth" : "aS", "()F", null, null);
	          
	          mv.visitCode();
	          Label start = new Label();
	          mv.visitLabel(start);
	          mv.visitVarInsn(Opcodes.ALOAD, 0);
	          mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "getHealth", "(Lnet/minecraft/entity/EntityLivingBase;)F", false);
	          
	          mv.visitInsn(Opcodes.FRETURN);
	          Label end = new Label();
	          mv.visitLabel(end);
	          mv.visitLocalVariable("this", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
	          mv.visitMaxs(1, 1);
	          mv.visitEnd();
	          mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, Core.debug ? "getMaxHealth" : "aY", "()F", null, null);
	          
	          mv.visitCode();
	          start = new Label();
	          mv.visitLabel(start);
	          mv.visitVarInsn(Opcodes.ALOAD, 0);
	          mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "getMaxHealth", "(Lnet/minecraft/entity/EntityLivingBase;)F", false);
	          
	          mv.visitInsn(Opcodes.FRETURN);
	          end = new Label();
	          mv.visitLabel(end);
	          mv.visitLocalVariable("this", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
	          mv.visitMaxs(1, 1);
	          mv.visitEnd();
	        }
        	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        		if(name.equals("activePotionsMap") || name.equals("f")) {
        			access = Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL;
        		}
        		return this.cv.visitField(access, name, desc, signature, value);
        	}
	        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	        {
	          if (((name.equals("aS")) && (desc.equals("()F"))) || (name.equals("getHealth"))) {
	            return this.cv.visitMethod(access, "getHealth2", desc, signature, exceptions);
	          }
	          if(name.equals("addPotionEffect") || name.equals("c") && (desc.equals("(Lnet/minecraft/potion/PotionEffect;)V") || desc.equals("(Lrw;)V"))) {
	              MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
	              mv.visitCode();
	              Label start = new Label();
	              mv.visitLabel(start);
	              mv.visitVarInsn(Opcodes.ALOAD, 0);
	              mv.visitVarInsn(Opcodes.ALOAD, 1);
	              mv.visitMethodInsn(Opcodes.INVOKESTATIC, "xuanwu/ultimate/core/util/EventUtil", "addPotionEffect", "(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/potion/PotionEffect;)V", false);
	              
	              mv.visitInsn(Opcodes.RETURN);
	              Label end = new Label();
	              mv.visitLabel(end);
	              mv.visitLocalVariable("this", "Lnet/minecraft/entity/EntityLivingBase;", null, start, end, 0);
	              
	              mv.visitLocalVariable("p_70690_1_", "Lnet/minecraft/potion/PotionEffect;", null, start, end, 1);
	              mv.visitMaxs(2, 2);
	              mv.visitEnd();
	              return null;
	          }
	          if (((name.equals("aY")) && (desc.equals("()F"))) || (name.equals("getMaxHealth"))) {
	            return this.cv.visitMethod(access, "getMaxHealth2", desc, signature, exceptions);
	          }
	            if (name.equals("getHealth2")) {
	                return null; 
	            }
	              if (name.equals("getMaxHealth2")) {
	                return null; 
	              }
	              if(name.equals("onChangedPotionEffect") || name.equals("a") && (desc.equals("(Lnet/minecraft/potion/PotionEffect;Z)V") || desc.equals("(Lrw;Z)V"))) {
	            	  access = Opcodes.ACC_PUBLIC;
	              }
	              if(name.equals("onNewPotionEffect") || name.equals("a") && (desc.equals("(Lnet/minecraft/potion/PotionEffect;)V") || desc.equals("(Lrw;)V"))) {
	            	  access = Opcodes.ACC_PUBLIC;
	              }
	            return this.cv.visitMethod(access, name, desc, signature, exceptions);
	          }
	        };
	        classReader.accept(classVisitor, Opcodes.ASM4);
	        return classWriter.toByteArray();
	      }
	return basicClass;
  }
      }
