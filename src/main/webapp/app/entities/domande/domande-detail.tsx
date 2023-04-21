import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './domande.reducer';

export const DomandeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const domandeEntity = useAppSelector(state => state.domande.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="domandeDetailsHeading">Domande</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{domandeEntity.id}</dd>
          <dt>
            <span id="descrizione">Descrizione</span>
          </dt>
          <dd>{domandeEntity.descrizione}</dd>
        </dl>
        <Button tag={Link} to="/domande" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Indietro</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/domande/${domandeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Modifica</span>
        </Button>
      </Col>
    </Row>
  );
};

export default DomandeDetail;
