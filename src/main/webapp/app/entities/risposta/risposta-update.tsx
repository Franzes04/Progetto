import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDomande } from 'app/shared/model/domande.model';
import { getEntities as getDomandes } from 'app/entities/domande/domande.reducer';
import { IPersona } from 'app/shared/model/persona.model';
import { getEntities as getPersonas } from 'app/entities/persona/persona.reducer';
import { IRisposta } from 'app/shared/model/risposta.model';
import { getEntity, updateEntity, createEntity, reset } from './risposta.reducer';

export const RispostaUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const domandes = useAppSelector(state => state.domande.entities);
  const personas = useAppSelector(state => state.persona.entities);
  const rispostaEntity = useAppSelector(state => state.risposta.entity);
  const loading = useAppSelector(state => state.risposta.loading);
  const updating = useAppSelector(state => state.risposta.updating);
  const updateSuccess = useAppSelector(state => state.risposta.updateSuccess);

  const handleClose = () => {
    navigate('/risposta');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDomandes({}));
    dispatch(getPersonas({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...rispostaEntity,
      ...values,
      domande: domandes.find(it => it.id.toString() === values.domande.toString()),
      persona: personas.find(it => it.id.toString() === values.persona.toString()),
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
          ...rispostaEntity,
          domande: rispostaEntity?.domande?.id,
          persona: rispostaEntity?.persona?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="progettoAlternanzaApp.risposta.home.createOrEditLabel" data-cy="RispostaCreateUpdateHeading">
            Genera o modifica un Risposta
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="risposta-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Risposte" id="risposta-risposte" name="risposte" data-cy="risposte" type="text" />
              <ValidatedField id="risposta-domande" name="domande" data-cy="domande" label="Domande" type="select">
                <option value="" key="0" />
                {domandes
                  ? domandes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.descrizione}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="risposta-persona" name="persona" data-cy="persona" label="Persona" type="select">
                <option value="" key="0" />
                {personas
                  ? personas.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.nome}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/risposta" replace color="info">
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

export default RispostaUpdate;
