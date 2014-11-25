package com.example.coppermod.handler;

import com.example.coppermod.CopperMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;

import java.util.Random;

/**
 * Created by atvaccaro on 8/22/14.
 */
public class EntityHandler {
    public static void registerEntities(Class entityClass, String name)
    {
        int entityID = EntityRegistry.findGlobalUniqueEntityId();
        long x = name.hashCode();
        Random random = new Random(x);
        int mainColor = random.nextInt() * 16777215; //first egg color
        int subColor = random.nextInt() * 16777215; //second egg color

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
        EntityRegistry.registerModEntity(entityClass, name, entityID, CopperMod.instance, 64, 1, true);
                                    //64 = range for tracking updates
                                    //1 = frequency of tracking updates
                                    //true = whether tracking updates are sent??

        //Register egg
        EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, mainColor, subColor));


    }
}
