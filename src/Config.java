import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "config")
public class Config {
    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    public Save getSave() {
        return save;
    }

    public void setSave(Save save) {
        this.save = save;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    @JacksonXmlProperty(isAttribute = true)
    private Load load;
    @JacksonXmlProperty(isAttribute = true)
    private Save save;
    @JacksonXmlProperty(isAttribute = true)
    private Log log;
}
