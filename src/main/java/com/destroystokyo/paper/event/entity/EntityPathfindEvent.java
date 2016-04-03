package com.destroystokyo.paper.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired when an Entity decides to start moving towards a location.
 *
 * This event does not fire for the entities actual movement. Only when it
 * is choosing to start moving to a location.
 */
public class EntityPathfindEvent extends Event implements Cancellable {
    private final Entity entity;
    private final Entity targetEntity;
    private final Location loc;
    public EntityPathfindEvent(Entity entity, Location loc, Entity targetEntity) {
        this.entity = entity;
        this.targetEntity = targetEntity;
        this.loc = loc;
    }

    /**
     * The Entity that is pathfinding.
     * @return The Entity that is pathfinding.
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * If the Entity is trying to pathfind to an entity, this is the entity in relation.
     *
     * Otherwise this will return null.
     *
     * @return The entity target or null
     */
    public Entity getTargetEntity() {
        return targetEntity;
    }

    /**
     * The Location of where the entity is about to move to.
     *
     * Note that if the target happened to of been an entity
     * @return Location of where the entity is trying to pathfind to.
     */
    public Location getLoc() {
        return loc;
    }

    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private boolean cancelled = false;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}