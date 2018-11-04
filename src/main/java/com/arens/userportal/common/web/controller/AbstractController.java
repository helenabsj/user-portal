package com.arens.userportal.common.web.controller;

import com.arens.userportal.common.entity.IEntity;
import com.arens.userportal.common.web.util.RestPreconditions;
import com.arens.userportal.common.service.IService;


import java.io.Serializable;

public abstract class  AbstractController <T extends IEntity> {

    // update

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
    protected final void updateInternal(final long id, final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource, "Element is null");


        RestPreconditions.checkRequestElementNotNull(resource.getId(), "ID is null");
        RestPreconditions.checkRequestState(resource.getId() == id, "parameter and element id don't match");



    }

    // template method

    protected abstract IService<T> getService();
}
