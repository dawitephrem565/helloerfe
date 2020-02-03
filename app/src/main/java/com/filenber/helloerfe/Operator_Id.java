package com.filenber.helloerfe;

public class Operator_Id {
    String Id;
    public <T extends Operator_Id> T withId(final String id)
    {
        this.Id=id;
        return (T)this;
    }
}
