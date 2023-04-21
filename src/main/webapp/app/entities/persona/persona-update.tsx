import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPersona } from 'app/shared/model/persona.model';
import { getEntity, updateEntity, createEntity, reset } from './persona.reducer';

export const PersonaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const personaEntity = useAppSelector(state => state.persona.entity);
  const loading = useAppSelector(state => state.persona.loading);
  const updating = useAppSelector(state => state.persona.updating);
  const updateSuccess = useAppSelector(state => state.persona.updateSuccess);

  const handleClose = () => {
    navigate('/persona');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...personaEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...personaEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="progettoAlternanzaApp.persona.home.createOrEditLabel" data-cy="PersonaCreateUpdateHeading">
            Genera o modifica un Persona
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="persona-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Nome" id="persona-nome" name="nome" data-cy="nome" type="text" />
              <ValidatedField label="Cognome" id="persona-cognome" name="cognome" data-cy="cognome" type="text" />
              <ValidatedField label="Data Nascita" id="persona-dataNascita" name="dataNascita" data-cy="dataNascita" type="date" />
              <ValidatedField label="Email" id="persona-email" name="email" data-cy="email" type="text" />
              <ValidatedField label="Citta" id="persona-citta" name="citta" data-cy="citta" type="text" />
              <ValidatedField label="Eta" id="persona-eta" name="eta" data-cy="eta" type="text" />
              <ValidatedField label="Telefono" id="persona-telefono" name="telefono" data-cy="telefono" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/persona" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Indietro</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Salva
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PersonaUpdate;
