package cn.cesgroup.cesweb.monitor.admin.support;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 服务状态变化通知
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 9:11:30 AM
 * @version 1.0.2020
 */
@Slf4j
@Component
public class StatusChangeNotifier extends AbstractEventNotifier {

	protected StatusChangeNotifier(InstanceRepository repository) {
		super(repository);
	}

	@Override
	protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
		return Mono.fromRunnable(() -> {
			if (event instanceof InstanceStatusChangedEvent) {
				log.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
			}
			else {
				log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(), event.getType());
			}
		});
	}

}