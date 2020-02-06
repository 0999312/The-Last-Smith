package cn.mmf.lastsmith.event;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RegisterSlashBladeEvent extends Event {
	private final FMLInitializationEvent event;
	public RegisterSlashBladeEvent(FMLInitializationEvent call_event) {
		event = call_event;
	}
	public FMLInitializationEvent getEvent() {
		return event;
	}
}
