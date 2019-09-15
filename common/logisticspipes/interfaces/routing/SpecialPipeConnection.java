package logisticspipes.interfaces.routing;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.util.math.Direction;

import logisticspipes.proxy.specialconnection.SpecialPipeConnectionRegistry.ConnectionInformation;
import logisticspipes.routing.PipeRoutingConnectionType;
import logisticspipes.routing.pathfinder.IPipeInformationProvider;

public interface SpecialPipeConnection {

	boolean init();

	boolean isType(IPipeInformationProvider startPipe);

	List<ConnectionInformation> getConnections(IPipeInformationProvider startPipe, EnumSet<PipeRoutingConnectionType> connection, Direction side);
}