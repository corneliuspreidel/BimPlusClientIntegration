package bimplus.api.Wrapper;

import bimplus.api.ApiCore;
import bimplus.data.DtoAttachment;
import bimplus.data.DtoIssue;
import bimplus.data.DtoPin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by Cornelius on 05.08.2016.
 */
public class Pins extends BaseWrapper
{
    private static final Logger LOG = LoggerFactory.getLogger(Issues.class);

    public Pins(ApiCore core)
    {
        super(core);
    }

    public List<DtoPin> GetPins(String issueId)
    {
        try
        {
            String json = core.connection.sendGetRequest( core.GetV2TeamUrl() + "/issues/" + issueId + "/pins");
            List<DtoPin> pins = core.mapper.readValue(json, core.mapper.getTypeFactory().constructCollectionType(List.class, DtoPin.class));
            return pins;
        }
        catch(IOException e)
        {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public DtoPin GetPinDetails(String pinId)
    {
        try
        {
            String json = core.connection.sendGetRequest( core.GetV2TeamUrl() + "/pins/" + pinId);
            DtoPin pin = core.mapper.readValue(json, DtoPin.class);
            return pin;
        }
        catch(IOException e)
        {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }
}